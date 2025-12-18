package com.example.navigation.impl.entry

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.navigation.api.MainNavKey
import com.example.presentation.main.MainScreen

/**
 * 메인 화면 Entry Builder
 */
fun EntryProviderScope<NavKey>.mainEntryBuilder(
    onNavigateToLogin: () -> Unit,
    onNavigateBack: () -> Unit
) {
    entry<MainNavKey> {
        MainScreen(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateBack = onNavigateBack
        )
    }
}