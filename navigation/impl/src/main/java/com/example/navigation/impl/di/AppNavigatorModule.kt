package com.example.navigation.impl.di

import androidx.compose.runtime.MutableState
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.AppNavigator
import com.example.navigation.impl.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppNavigatorModule {

    @Provides
    @ActivityRetainedScoped
    fun provideBackStackState(): MutableState<List<NavKey>> {
        return androidx.compose.runtime.mutableStateOf(
            listOf(com.example.navigation.api.SplashNavKey)
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideAppNavigator(
        backStackState: MutableState<List<NavKey>>
    ): AppNavigator {
        return AppNavigatorImpl(backStackState)
    }
}
