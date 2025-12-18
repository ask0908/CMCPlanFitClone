package com.example.navigation.api.route

import kotlinx.serialization.Serializable

/**
 * 온보딩 화면 라우트
 *
 * @param step 현재 온보딩 단계
 */
@Serializable
data class OnboardingRoute(val step: Int = 1)