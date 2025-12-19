package com.example.presentation.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.card.PlanfitSelectableCard
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.component.textfield.PlanfitTextField
import com.example.designsystem.foundation.Spacing
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@Composable
fun OnboardingScreen7(
    onNavigateToNextStep: () -> Unit = {},
) {
    var birthday by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

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
            text = "신체 정보를 알려주세요",
            fontSize = Spacing.dp24
        )

        Spacer(modifier = Modifier.height(Spacing.dp12))

        PlanfitText(
            text = "적절한 운동 추천에 필요해요! 외부에 공개되지 않아요",
            fontSize = Spacing.dp16,
        )

        Spacer(modifier = Modifier.height(Spacing.dp60))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlanfitText(
                    modifier = Modifier.widthIn(min = Spacing.dp75),
                    text = "생년월일",
                    fontSize = Spacing.dp20,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(Spacing.dp20))
                PlanfitTextField(
                    value = birthday,
                    onValueChange = { newValue ->
                        birthday = newValue
                    },
                    showLeadingIcon = false
                )
            }

            Spacer(modifier = Modifier.height(Spacing.dp20))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlanfitText(
                    modifier = Modifier.widthIn(min = Spacing.dp75),
                    text = "키",
                    fontSize = Spacing.dp20,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(Spacing.dp20))
                PlanfitTextField(
                    value = height,
                    onValueChange = { newValue ->
                        height = newValue
                    },
                    showLeadingIcon = false
                )
            }

            Spacer(modifier = Modifier.height(Spacing.dp20))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlanfitText(
                    modifier = Modifier.widthIn(min = Spacing.dp75),
                    text = "몸무게",
                    fontSize = Spacing.dp20,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(Spacing.dp20))
                PlanfitTextField(
                    value = weight,
                    onValueChange = { newValue ->
                        weight = newValue
                    },
                    showLeadingIcon = false
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
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
private fun OnboardingScreen7Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen7()
    }
}