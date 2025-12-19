package com.example.presentation.onboarding

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
fun OnboardingScreen3(
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
            text = "다니시는 헬스장은 어디인가요?",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "헬스장에 있는 기구에 딱 맞게 추천해 딀게요",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitSelectableCard(
            leftText = "클릭해서 다음 화면으로",
            onClick = {
                onNavigateToNextStep()
            }
        )

        // TODO : 주소 검색 TextField 생성
    }
}