package com.example.navigation.impl.entry

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.OnboardingNavKey
import com.example.presentation.onboarding.OnboardingScreen

/**
 * 온보딩 화면 Entry Builder
 */
fun EntryProviderScope<NavKey>.onboardingEntry(
    onNavigateToNextStep: (Int) -> Unit,
    onNavigateToPreviousStep: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    entry<OnboardingNavKey> { navKey ->
        OnboardingScreen(
            currentStep = navKey.step,
            onNavigateToNextStep = onNavigateToNextStep,
            onNavigateToPreviousStep = onNavigateToPreviousStep,
            onNavigateToMain = onNavigateToMain
        )
    }
}