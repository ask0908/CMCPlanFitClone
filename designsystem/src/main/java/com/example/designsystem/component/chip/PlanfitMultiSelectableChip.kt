package com.example.designsystem.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.designsystem.theme.NotoSans
import com.example.designsystem.theme.SelectableCardBackground
import com.example.designsystem.theme.SplashMintText
import kotlinx.coroutines.delay

@Composable
fun PlanfitMultiSelectableChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    // 깜박임 효과 상태
    var blinkTrigger by remember { mutableStateOf(0) }
    var shouldBlink by remember { mutableStateOf(false) }

    LaunchedEffect(blinkTrigger) {
        if (blinkTrigger > 0) {
            shouldBlink = true
            delay(150)
            shouldBlink = false
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (shouldBlink) Color.White.copy(alpha = 0.3f) else SelectableCardBackground,
        animationSpec = tween(durationMillis = 150),
        label = "background color"
    )

    val shape = RoundedCornerShape(Spacing.dp8)

    Text(
        text = text,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = Spacing.dp1,
                        color = SplashMintText,
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .clickable {
                blinkTrigger++
                onClick()
            }
            .padding(
                horizontal = Spacing.dp12,
                vertical = Spacing.dp8
            ),
        color = Color.White,
        fontFamily = NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlanfitMultiSelectableChipGroup(
    modifier: Modifier = Modifier,
    chips: List<String>,
    selectedChips: List<String> = emptyList(),
    onChipClick: (String) -> Unit = {}
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.dp8),
        verticalArrangement = Arrangement.spacedBy(Spacing.dp8)
    ) {
        chips.forEach { chip ->
            PlanfitMultiSelectableChip(
                text = chip,
                isSelected = selectedChips.contains(chip),
                onClick = { onChipClick(chip) }
            )
        }
    }
}

@Preview
@Composable
private fun PlanfitMultiSelectableChipPreview() {
    CMCPlanFitCloneTheme {
        val selectedChips = remember { mutableStateListOf<String>() }
        val chipList = listOf(
            "평생 숙제 다이어트", "뱃살, 옆구리살 빼기", "팔뚝 군살 제거", "슬림한 하체 라인 만들기", "벌크업 하기", "넓은 어깨 갖기", "탄탄한 몸 만들기", "힙업"
        )

        PlanfitMultiSelectableChipGroup(
            chips = chipList,
            selectedChips = selectedChips,
            onChipClick = { chip ->
                if (selectedChips.contains(chip)) {
                    selectedChips.remove(chip)
                } else {
                    selectedChips.add(chip)
                }
            }
        )
    }
}