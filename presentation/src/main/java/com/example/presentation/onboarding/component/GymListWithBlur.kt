package com.example.presentation.onboarding.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.presentation.onboarding.data.GymInfo
import com.example.presentation.onboarding.data.getDummyGymList
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@Composable
fun GymListWithBlur(
    gyms: List<GymInfo>,
    onGymClick: (GymInfo) -> Unit = {},
    onRegisterRequest: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = Spacing.dp20),
            verticalArrangement = Arrangement.spacedBy(Spacing.dp12)
        ) {
            items(gyms) { gym ->
                GymListItem(
                    gym = gym,
                    onClick = { onGymClick(gym) }
                )
            }

            // 맨 밑 블러 영역
            item {
                Spacer(modifier = Modifier.height(Spacing.dp60))
            }
        }

        // 하단 그라디언트 블러 오버레이
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(Spacing.dp80)
                .drawBehind {
                    val gradientHeight = size.height
                    val gradientBrush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = gradientHeight,
                    )
                    drawRect(
                        brush = gradientBrush,
                        topLeft = Offset(x = 0f, y = 0f),
                        size = Size(
                            width = size.width,
                            height = gradientHeight,
                        )
                    )
                }
        )

        // 헬스장 등록 신청하기 텍스트
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Spacing.dp16)
        ) {
            PlanfitText(
                text = "헬스장 등록 신청하기",
                color = Color.White,
                fontSize = Spacing.dp14,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onRegisterRequest() }
            )
        }
    }
}

@Preview
@Composable
private fun GymListWithBlurPreview() {
    CMCPlanFitCloneTheme {
        GymListWithBlur(
            gyms = getDummyGymList()
        )
    }
}