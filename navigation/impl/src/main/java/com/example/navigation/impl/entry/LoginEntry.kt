package com.example.navigation.impl.entry

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.LoginNavKey
import com.example.presentation.login.LoginScreen

fun EntryProviderScope<NavKey>.loginEntryBuilder(
    onNavigateToOnboarding: () -> Unit,
) {
    entry<LoginNavKey> {
        LoginScreen(
            onNavigateToOnboarding = onNavigateToOnboarding,
        )
    }
}