package com.example.presentation.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.card.PlanfitSelectableCard
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@Composable
fun OnboardingScreen2(
    onNavigateToNextStep: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Spacing.dp20,
                vertical = Spacing.dp12,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        PlanfitText(
            text = "주로 어디서 운동하시나요?",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "장소에 맞게 운동을 구성해 드릴게요.",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column {
            PlanfitSelectableCard(
                leftText = "헬스장",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "집",
                onClick = {
                    onNavigateToNextStep()
                }
            )
        }

        Spacer(modifier = Modifier.height(Spacing.dp24))
    }
}

@Preview
@Composable
private fun OnboardingScreen2Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen2()
    }
}