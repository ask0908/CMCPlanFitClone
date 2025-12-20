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
import com.example.designsystem.component.card.PlanfitSelectableCard
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing

@Composable
fun OnboardingScreen6(
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
            text = "성별이 어떻게 되시나요?",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "적절한 운동 추천에 필요해요! 외부에 공개되지 않아요",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column {
            PlanfitSelectableCard(
                leftText = "남성",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "여성",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "기타",
                onClick = {
                    onNavigateToNextStep()
                }
            )
        }

        Spacer(modifier = Modifier.height(Spacing.dp24))
    }
}