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
fun OnboardingScreen1(
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
            text = "운동 수준이 어떻게 되시나요?",
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
                leftText = "입문",
                rightText = "운동 자세, 루틴 등 아무것도 몰라요",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "초급",
                rightText = "자세는 조금 알지만 무슨 운동을 해야 할지 몰라요",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "중급",
                rightText = "운동 자세를 잘 알고, 나만의 루틴이 있어요",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "고급",
                rightText = "운동을 직업으로 삼을 만큼의 지식이 있어요",
                onClick = {
                    onNavigateToNextStep()
                }
            )
            Spacer(modifier = Modifier.height(Spacing.dp8))
            PlanfitSelectableCard(
                leftText = "전문가",
                rightText = "운동 선수급의 지식과 경험을 갖고 있어요",
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
private fun OnboardingScreen1Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen1()
    }
}