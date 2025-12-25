package com.example.presentation.onboarding.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.component.textfield.PlanfitTextField
import com.example.designsystem.foundation.Spacing
import com.example.presentation.onboarding.component.DatePickerContent
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen7(
    onNavigateToNextStep: () -> Unit = {},
) {
    var birthday by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    // 스크롤하는 데이트피커 관련
    var showDatePicker by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(Spacing.dp12))
                        .background(Color(0xFF3D3F46))
                        .clickable(
                            onClick = { showDatePicker = true },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    PlanfitText(
                        text = birthday.ifEmpty { "" },
                        fontSize = Spacing.dp16,
                        color = if (birthday.isEmpty()) {
                            Color.White.copy(alpha = 0.5f)
                        } else {
                            Color.White
                        }
                    )
                }
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
                    showLeadingIcon = false,
                    suffix = "cm",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
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
                    showLeadingIcon = false,
                    suffix = "kg",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
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

    // 날짜 선택 바텀시트
    if (showDatePicker) {
        ModalBottomSheet(
            onDismissRequest = { showDatePicker = false },
            sheetState = bottomSheetState
        ) {
            DatePickerContent(
                onConfirm = { year, month, day ->
                    birthday = "${year}년 ${month}월 ${day}일"
                    showDatePicker = false
                },
                onCancel = {
                    showDatePicker = false
                }
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingScreen7Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen7()
    }
}