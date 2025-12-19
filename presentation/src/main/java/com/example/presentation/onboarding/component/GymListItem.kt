package com.example.presentation.onboarding.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.min
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.foundation.Spacing
import com.example.presentation.onboarding.data.GymInfo
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

@Composable
fun GymListItem(
    gym: GymInfo,
    onClick: () -> Unit = {},
    isSelected: Boolean = false,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF5A5D66) else Color.Transparent,
        animationSpec = tween(durationMillis = 150),
        label = "backgroundColorAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(Spacing.dp12)
            )
            .clickable { onClick() }
            .padding(Spacing.dp16),
        verticalArrangement = Arrangement.spacedBy(Spacing.dp8)
    ) {
        PlanfitText(
            text = gym.name,
            fontSize = Spacing.dp16,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.dp8),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PlanfitText(
                text = "도로명",
                modifier = Modifier
                    .widthIn(min = Spacing.dp50)
                    .background(
                        color = Color(0xFFD7E8FE),
                        shape = RoundedCornerShape(Spacing.dp20)
                    ),
                color = Color(0xFF3468CC),
                fontSize = Spacing.dp10,
                textAlign = TextAlign.Center,
            )
            PlanfitText(
                text = gym.roadAddress,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = Spacing.dp12,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.dp8),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PlanfitText(
                text = "우편번호",
                modifier = Modifier
                    .widthIn(min = Spacing.dp50)
                    .background(
                        color = Color(0xFF636773),
                        shape = RoundedCornerShape(Spacing.dp12)
                    ),
                color = Color(0xFFC3C7D3),
                fontSize = Spacing.dp10,
                textAlign = TextAlign.Center,
            )
            PlanfitText(
                text = gym.zipCode,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = Spacing.dp12,
            )
        }
    }
}

@Preview
@Composable
private fun GymListItemPreview() {
    CMCPlanFitCloneTheme {
        GymListItem(
            gym = GymInfo(
                name = "스포애니 강남역2호점",
                roadAddress = "서울특별시 서초구 서초대로78길 11 (서초동, XX) ㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㄴㅁㄹㅇㄴㅁㄹㅇㅁㄴㄹㅇ",
                zipCode = "06626",
            )
        )
    }
}