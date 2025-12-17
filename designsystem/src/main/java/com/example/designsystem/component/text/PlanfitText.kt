package com.example.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.NotoSans

@Composable
fun PlanfitText(
    text: String,
    color: Color,
    fontSize: Dp? = null,
) {
    val currentLocalDensity = LocalDensity.current
    val textDefaultSize = fontSize?.let { size ->
        with(currentLocalDensity) {
            size.toSp()
        }
    }
    val textSize = with(currentLocalDensity) {
        Spacing.dp14.toSp()
    }

    Text(
        text = text,
        color = color,
        fontFamily = NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = textDefaultSize ?: textSize
    )
}