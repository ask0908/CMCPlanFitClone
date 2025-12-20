package com.example.navigation.api

import androidx.navigation3.runtime.NavKey

/**
 * 앱 전체 네비게이션 관리 인터페이스
 */
interface AppNavigator {
    fun navigate(destination: NavKey, popUpTo: NavKey? = null, inclusive: Boolean = false)
    fun popBackStack()
    fun navigateToLogin()
    fun navigateToMain()
    fun navigateToOnboarding()
    fun navigateToNextOnboardingStep(currentStep: Int)
    fun navigateToMainFromOnboarding()
    fun navigateToLoginFromMain()
}
