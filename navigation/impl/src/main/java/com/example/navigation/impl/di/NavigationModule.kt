package com.example.navigation.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.AppNavigator
import com.example.navigation.impl.entry.loginEntry
import com.example.navigation.impl.entry.mainEntry
import com.example.navigation.impl.entry.onboardingEntry
import com.example.navigation.impl.entry.splashEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavigationModule {

    @Provides
    @IntoSet
    fun provideSplashEntry(
        navigator: AppNavigator
    ): EntryProviderScope<NavKey>.() -> Unit = {
        splashEntry(
            onNavigateToLogin = { navigator.navigateToLogin() }
        )
    }

    @Provides
    @IntoSet
    fun provideLoginEntry(
        navigator: AppNavigator
    ): EntryProviderScope<NavKey>.() -> Unit = {
        loginEntry(
            onNavigateToOnboarding = { navigator.navigateToOnboarding() }
        )
    }

    @Provides
    @IntoSet
    fun provideOnboardingEntry(
        navigator: AppNavigator
    ): EntryProviderScope<NavKey>.() -> Unit = {
        onboardingEntry(
            onNavigateToNextStep = { currentStep ->
                navigator.navigateToNextOnboardingStep(currentStep)
            },
            onNavigateToPreviousStep = {
                navigator.popBackStack()
            },
            onNavigateToMain = {
                navigator.navigateToMainFromOnboarding()
            }
        )
    }

    @Provides
    @IntoSet
    fun provideMainEntry(
        navigator: AppNavigator
    ): EntryProviderScope<NavKey>.() -> Unit = {
        mainEntry(
            onNavigateToLogin = {
                navigator.navigateToLoginFromMain()
            },
            onNavigateBack = {
                navigator.popBackStack()
            }
        )
    }
}
