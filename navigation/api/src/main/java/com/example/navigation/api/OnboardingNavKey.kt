package com.example.navigation.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingNavKey(
    val step: Int = 1
) : NavKey