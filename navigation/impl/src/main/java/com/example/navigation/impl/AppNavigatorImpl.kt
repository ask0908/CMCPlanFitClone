package com.example.navigation.impl

import androidx.compose.runtime.MutableState
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.LoginNavKey
import com.example.navigation.api.MainNavKey
import com.example.navigation.api.OnboardingNavKey
import com.example.navigation.api.SplashNavKey

/**
 * 앱 전체 네비게이션 관리 구현체
 */
class AppNavigatorImpl(
    private val backStackState: MutableState<List<NavKey>>
) : AppNavigator {
    private val backStack: List<NavKey>
        get() = backStackState.value

    override fun navigate(destination: NavKey, popUpTo: NavKey?, inclusive: Boolean) {
        backStackState.value = if (popUpTo != null) {
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

    override fun popBackStack() {
        if (backStack.size > 1) {
            backStackState.value = backStack.dropLast(1)
        }
    }

    override fun navigateToLogin() =
        navigate(LoginNavKey, popUpTo = SplashNavKey, inclusive = true)

    override fun navigateToMain() =
        navigate(MainNavKey, popUpTo = SplashNavKey, inclusive = true)

    override fun navigateToOnboarding() =
        navigate(OnboardingNavKey(step = 1), popUpTo = LoginNavKey)

    override fun navigateToNextOnboardingStep(currentStep: Int) =
        navigate(OnboardingNavKey(step = currentStep + 1))

    override fun navigateToMainFromOnboarding() {
        val currentNavKey = backStack.lastOrNull()
        navigate(MainNavKey, popUpTo = currentNavKey, inclusive = true)
    }

    override fun navigateToLoginFromMain() =
        navigate(LoginNavKey, popUpTo = MainNavKey, inclusive = true)
}