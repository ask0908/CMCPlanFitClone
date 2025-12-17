package com.example.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.designsystem.foundation.Spacing.dp12
import com.example.designsystem.theme.NotoSans

@Composable
fun LoginTermsSection() {
    val currentLocalDensity = LocalDensity.current
    val loginPolicyTextSize = with (currentLocalDensity) {
        dp12.toSp()
    }

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(com.example.presentation.R.string.login_alert),
        style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center
        ),
        fontFamily = NotoSans,
        fontWeight = FontWeight.Normal,
    )
    Spacer(
        modifier = Modifier.fillMaxHeight(0.4f)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.example.presentation.R.string.personal_information_policy),
            style = TextStyle(
                color = Color.White,
                fontSize = loginPolicyTextSize,
                textDecoration = TextDecoration.Underline,
            ),
            fontFamily = NotoSans,
            fontWeight = FontWeight.Normal,
        )
        Spacer(
            modifier = Modifier.fillMaxWidth(0.15f)
        )
        Text(
            text = stringResource(com.example.presentation.R.string.terms_conditions),
            style = TextStyle(
                color = Color.White,
                fontSize = loginPolicyTextSize,
                textDecoration = TextDecoration.Underline,
            ),
            fontFamily = NotoSans,
            fontWeight = FontWeight.Normal,
        )
    }
}