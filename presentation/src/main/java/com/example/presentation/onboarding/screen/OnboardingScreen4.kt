package com.example.presentation.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.designsystem.component.card.PlanfitSelectableCard
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing

@Composable
fun OnboardingScreen4(
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
            text = "일주일에 몇 번 운동하실\n예정인가요?",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "언제든지 변경할 수 있어요",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.dp8)
        ) {
            // 왼쪽 Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Spacing.dp8)
            ) {
                PlanfitSelectableCard(
                    leftText = "1회",
                    onClick = { onNavigateToNextStep() }
                )
                PlanfitSelectableCard(
                    leftText = "3회",
                    onClick = { onNavigateToNextStep() }
                )
                PlanfitSelectableCard(
                    leftText = "5회",
                    onClick = { onNavigateToNextStep() }
                )
            }

            // 오른쪽 Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Spacing.dp8)
            ) {
                PlanfitSelectableCard(
                    leftText = "2회",
                    onClick = { onNavigateToNextStep() }
                )
                PlanfitSelectableCard(
                    leftText = "4회",
                    onClick = { onNavigateToNextStep() }
                )
                PlanfitSelectableCard(
                    leftText = "6회",
                    onClick = { onNavigateToNextStep() }
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.dp24))
    }
}