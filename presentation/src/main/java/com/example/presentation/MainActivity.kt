package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.LoginNavKey
import com.example.presentation.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var entryBuilders: Set<@JvmSuppressWildcards EntryProviderScope<NavKey>.() -> Unit>

    @Inject
    lateinit var backStackState: MutableState<List<NavKey>>

    @Inject
    lateinit var appNavigator: AppNavigator

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
                        backStackState = backStackState,
                        entryBuilders = entryBuilders,
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
    backStackState: MutableState<List<NavKey>>,
    entryBuilders: Set<EntryProviderScope<NavKey>.() -> Unit>,
    onBackPressedOnLogin: () -> Unit = {}
) {
    val backStack by backStackState

    // 로그인 화면에서 뒤로가기 빠르게 2번 눌렀을 때만 앱 종료
    closeAppInLoginScreen(backStack, onBackPressedOnLogin)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStackState.value = backStack.dropLast(1)
            }
        },
        entryProvider = entryProvider {
            entryBuilders.forEach { builder ->
                this.builder()
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