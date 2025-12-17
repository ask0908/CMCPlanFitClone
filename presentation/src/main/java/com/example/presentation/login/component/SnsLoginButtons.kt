package com.example.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.component.button.PlanfitSnsLoginButton
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.foundation.Spacing.dp32
import com.example.designsystem.theme.FacebookLoginButton
import com.example.designsystem.theme.NotoSans

@Composable
fun SnsLoginButtons(modifier: Modifier = Modifier) {
    PlanfitSnsLoginButton(
        text = stringResource(com.example.presentation.R.string.kakao_login),
        iconRes = R.drawable.kakao_symbol,
        onClick = {},
        backgroundColor = Color.Yellow,
        contentColor = Color.Black
    )
    Spacer(
        modifier = Modifier.fillMaxHeight(0.01f)
    )
    PlanfitSnsLoginButton(
        text = stringResource(com.example.presentation.R.string.apple_login),
        iconRes = R.drawable.apple_symbol,
        onClick = {},
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
    Spacer(
        modifier = Modifier.fillMaxHeight(0.1f)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
        )
        Text(
            modifier = Modifier.padding(
                horizontal = Spacing.dp8
            ),
            text = "또는",
            style = TextStyle(
                color = Color.White
            ),
            fontFamily = NotoSans,
            fontWeight = FontWeight.Normal,
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
        )
    }
    Spacer(
        modifier = Modifier.fillMaxHeight(0.08f)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlanfitSnsLoginButton(
            iconRes = R.drawable.google_symbol,
            onClick = {},
            backgroundColor = Color.White,
            iconSize = dp32
        )
        Spacer(
            modifier = Modifier.fillMaxWidth(0.15f)
        )
        PlanfitSnsLoginButton(
            iconRes = R.drawable.facebook_symbol,
            onClick = {},
            backgroundColor = FacebookLoginButton,
            iconSize = dp32
        )
    }
}