package com.example.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.presentation.onboarding.component.OnboardingProgressBar
import com.example.presentation.onboarding.screen.OnboardingScreen1
import com.example.presentation.onboarding.screen.OnboardingScreen2
import com.example.presentation.onboarding.screen.OnboardingScreen3
import com.example.presentation.onboarding.screen.OnboardingScreen4
import com.example.presentation.onboarding.screen.OnboardingScreen5
import com.example.presentation.onboarding.screen.OnboardingScreen6
import com.example.presentation.onboarding.screen.OnboardingScreen7

/**
 * Scaffold body 안에서 currentStep에 따라 when 써서 표시하는 컴포저블을 다르게 설정
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    currentStep: Int,
    onNavigateToNextStep: (Int) -> Unit = {},
    onNavigateToMain: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    PlanfitText(
                        text = getOnboardingTitle(currentStep),
                        color = Color.White,
                        fontSize = Spacing.dp20
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            OnboardingProgressBar(
                currentStep = currentStep,
                totalSteps = 12
            )

            when (currentStep) {
                // AppNavigator에서 currentStep + 1하기 때문에 여기선 +1할 필요 없음
                1 -> OnboardingScreen1(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                2 -> OnboardingScreen2(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                3 -> OnboardingScreen3(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                4 -> OnboardingScreen4(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                5 -> OnboardingScreen5(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                6 -> OnboardingScreen6(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                7 -> OnboardingScreen7(
                    onNavigateToNextStep = { onNavigateToNextStep(currentStep) }
                )
                8 -> {
                    //
                }
                9 -> {
                    //
                }
                10 -> {
                    //
                }
                11 -> {
                    //
                }
                12 -> {
                    //
                }
            }
        }
    }
}

private fun getOnboardingTitle(step: Int): String =
    when (step) {
        1 -> "운동 수준"
        2 -> "운동 장소"
        3 -> "헬스장 설정"
        4 -> "운동 목표"
        5 -> "신체 목표"
        6 -> "신체 정보"
        7 -> "추가 질문"
        else -> ""
    }

@Preview
@Composable
private fun OnboardingScreenPreview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen(
            currentStep = 1,
        )
    }
}