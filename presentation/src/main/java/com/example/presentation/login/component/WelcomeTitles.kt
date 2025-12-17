package com.example.presentation.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.foundation.Spacing.dp16
import com.example.designsystem.foundation.Spacing.dp24
import com.example.designsystem.theme.NotoSans
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme

private val WELCOME_BOTTOM_TEXT_COLOR = Color(0xFFA5A7A9)

@Composable
fun WelcomeTitles() {
    val currentLocalDensity = LocalDensity.current
    val loginTopTitleFontSize = with(currentLocalDensity) {
        dp24.toSp()
    }
    val loginBottomTitleFontSize = with(currentLocalDensity) {
        dp16.toSp()
    }

    Spacer(
        modifier = Modifier.fillMaxHeight(0.07f)
    )
    Text(
        text = stringResource(com.example.presentation.R.string.login_screen_top),
        style = TextStyle(
            color = Color.White
        ),
        fontFamily = NotoSans,
        fontWeight = FontWeight.ExtraBold,
        fontSize = loginTopTitleFontSize
    )
    Spacer(
        modifier = Modifier.height(Spacing.dp20)
    )
    Text(
        text = stringResource(com.example.presentation.R.string.login_screen_bottom),
        style = TextStyle(
            color = WELCOME_BOTTOM_TEXT_COLOR
        ),
        fontFamily = NotoSans,
        fontWeight = FontWeight.ExtraBold,
        fontSize = loginBottomTitleFontSize
    )
}

@Preview
@Composable
private fun WelcomeTitlesPreview() {
    CMCPlanFitCloneTheme {
        Column {
            WelcomeTitles()
        }
    }
}