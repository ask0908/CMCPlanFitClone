package com.example.presentation.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.card.PlanfitSelectableCard
import com.example.designsystem.component.chip.PlanfitMultiSelectableChipGroup
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@Composable
fun OnboardingScreen5(
    onNavigateToNextStep: () -> Unit = {},
) {
    val targetList = listOf(
        "평생 숙제 다이어트",
        "뱃살, 옆구리살 빼기",
        "팔뚝 군살 제거",
        "슬림한 하체 라인 만들기",
        "벌크업 하기",
        "넓은 어깨 갖기",
        "마른 몸 벗어나기",
        "굵고 큰 팔 만들기",
        "탄탄한 몸 만들기",
        "힙업",
        "전체적인 근육량 증가",
        "선명한 복근 만들기",
    )
    val selectedChips = remember { mutableStateListOf<String>() }

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
            text = "주요 목표는 무엇인가요?",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "적절한 운동 추천에 필요해요! 외부에 공개되지 않아요",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.weight(1f))

        PlanfitMultiSelectableChipGroup(
            chips = targetList,
            selectedChips = selectedChips,
            onChipClick = { chip ->
                if (selectedChips.contains(chip)) {
                    selectedChips.remove(chip)
                } else {
                    selectedChips.add(chip)
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(Spacing.dp20),
            onClick = {
                onNavigateToNextStep()
            }
        ) {
            Text("다음")
        }

        Spacer(modifier = Modifier.height(Spacing.dp24))
    }
}

@Preview
@Composable
private fun OnboardingScreen5Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen5()
    }
}