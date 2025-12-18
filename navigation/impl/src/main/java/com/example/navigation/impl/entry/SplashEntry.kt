package com.example.navigation.impl.entry

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.SplashNavKey
import com.example.presentation.splash.SplashScreen

fun EntryProviderScope<NavKey>.splashEntryBuilder(
    onNavigateToLogin: () -> Unit,
) {
    entry<SplashNavKey> {
        SplashScreen(
            onNavigateToLogin = onNavigateToLogin,
        )
    }
}