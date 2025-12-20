package com.example.designsystem.component.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.designsystem.theme.NotoSans
import com.example.designsystem.theme.SelectableCardBackground
import com.example.designsystem.theme.SplashMintText
import kotlinx.coroutines.delay

@Composable
fun PlanfitSelectableCard(
    modifier: Modifier = Modifier,
    leftText: String,
    rightText: String? = null,
    bottomText: String? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    leftTextAlignment: Alignment.Horizontal = Alignment.Start
) {
    var shouldBlink by remember(isSelected) { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            shouldBlink = true
            delay(150)
            shouldBlink = false
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (shouldBlink) Color.White.copy(alpha = 0.3f) else SelectableCardBackground,
        animationSpec = tween(durationMillis = 150),
    )
    val mintBorderShape = RoundedCornerShape(Spacing.dp12)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(Spacing.dp12)
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = Spacing.dp1,
                        color = SplashMintText,
                        shape = mintBorderShape
                    )
                } else {
                    Modifier
                }
            )
            .clickable { onClick() }
            .padding(Spacing.dp16)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = when {
                rightText == null -> Arrangement.Center
                else -> Arrangement.Start
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlanfitText(
                text = leftText,
                color = Color.White,
                fontSize = Spacing.dp18
            )

            rightText?.let {
                Spacer(modifier = Modifier.width(Spacing.dp16))
                PlanfitText(
                    text = it,
                    color = Color.White
                )
            }
        }

        // 하단 텍스트 애니메이션
        AnimatedVisibility(
            visible = isSelected && bottomText != null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                Spacer(modifier = Modifier.height(Spacing.dp12))
                PlanfitText(
                    text = bottomText ?: "",
                    color = Color.White.copy(alpha = 0.7f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlanfitSelectableCardPreview() {
    CMCPlanFitCloneTheme {
        Column {
            var selectedIndex by remember { mutableStateOf<Int?>(null) }

            PlanfitSelectableCard(
                leftText = "입문",
                rightText = "운동 자세, 운동 루틴 등 아무것도 몰라요",
                bottomText = "걱정 마세요, 제가 친절히 알려드릴게요!",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0 }
            )

            Spacer(modifier = Modifier.height(Spacing.dp12))

            PlanfitSelectableCard(
                leftText = "초급",
                rightText = "자세는 조금 알지만 무슨 운동을 해야 할지 몰라요",
                bottomText = "제가 적절한 운동 플랜을 잘 추천해 드릴게요",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1 }
            )
        }
    }
}