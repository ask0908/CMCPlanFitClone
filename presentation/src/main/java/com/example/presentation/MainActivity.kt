package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.navigation.api.LoginNavKey
import com.example.navigation.api.MainNavKey
import com.example.navigation.api.OnboardingNavKey
import com.example.navigation.api.SplashNavKey
import com.example.presentation.login.LoginScreen
import com.example.presentation.main.MainScreen
import com.example.presentation.onboarding.OnboardingScreen
import com.example.presentation.splash.SplashScreen
import com.example.presentation.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // 로그인 화면에서 뒤로가기 2번 클릭 시 앱 종료
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CMCPlanFitCloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(
                        onBackPressedOnLogin = {
                            handleBackPressOnLogin()
                        }
                    )
                }
            }
        }
    }

    private fun handleBackPressOnLogin() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000) {
            finish()
        } else {
            backPressedTime = currentTime
            AppUtil.showToast(this, "뒤로가기 키를 다시 한 번 누르면 앱이 종료됩니다")
        }
    }
}

@Composable
private fun AppNavigation(
    onBackPressedOnLogin: () -> Unit = {}
) {
    var backStack by remember {
        mutableStateOf(listOf<NavKey>(SplashNavKey))
    }

    fun navigate(
        destination: NavKey,
        popUpTo: NavKey? = null,
        inclusive: Boolean = false
    ) {
        backStack = if (popUpTo != null) {
            val index = backStack.indexOfLast { it == popUpTo }
            val newStack = if (index >= 0) {
                if (inclusive) {
                    backStack.subList(0, index)
                } else {
                    backStack.subList(0, index + 1)
                }
            } else {
                backStack
            }
            newStack + destination
        } else {
            backStack + destination
        }
    }

    fun popBackStack() {
        if (backStack.size > 1) {
            backStack = backStack.dropLast(1)
        }
    }

    // 로그인 화면에서 뒤로가기 빠르게 2번 눌렀을 때만 앱 종료
    closeAppInLoginScreen(backStack, onBackPressedOnLogin)

    NavDisplay(
        backStack = backStack,
        onBack = { popBackStack() },
        entryProvider = { navKey ->
            when (navKey) {
                is SplashNavKey -> NavEntry(navKey) {
                    SplashScreen(
                        onNavigateToLogin = {
                            navigate(LoginNavKey,
                                popUpTo = SplashNavKey,
                                inclusive = true
                            )
                        },
                    )
                }

                is LoginNavKey -> NavEntry(navKey) {
                    LoginScreen(
                        onNavigateToOnboarding = {
                            navigate(
                                OnboardingNavKey(step = 1),
                            )
                        },
                    )
                }

                is OnboardingNavKey -> NavEntry(navKey) {
                    OnboardingScreen(
                        currentStep = navKey.step,
                        onNavigateToNextStep = { currentStep ->
                            navigate(OnboardingNavKey(step = currentStep + 1))
                        },
                        onNavigateToPreviousStep = {
                            popBackStack()
                        },
                        onNavigateToMain = {
                            navigate(MainNavKey, popUpTo = navKey, inclusive = true)
                        }
                    )
                }

                is MainNavKey -> NavEntry(navKey) {
                    MainScreen(
                        onNavigateToLogin = {
                            navigate(LoginNavKey,
                                popUpTo = MainNavKey,
                                inclusive = true
                            )
                        },
                        onNavigateBack = {
                            popBackStack()
                        }
                    )
                }

                else -> throw IllegalArgumentException("NavKey가 이상해요 : $navKey")
            }
        }
    )
}

@Composable
private fun closeAppInLoginScreen(
    backStack: List<NavKey>,
    onBackPressedOnLogin: () -> Unit
) {
    val currentScreen = backStack.lastOrNull()
    if (currentScreen is LoginNavKey) {
        BackHandler {
            onBackPressedOnLogin()
        }
    }
}