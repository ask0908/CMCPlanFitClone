package com.example.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.designsystem.foundation.Dimension.snsLoginButtonFontSize
import com.example.designsystem.foundation.Dimension.snsLoginButtonRadius
import com.example.designsystem.foundation.Spacing.dp0
import com.example.designsystem.foundation.Spacing.dp12
import com.example.designsystem.foundation.Spacing.dp24
import com.example.designsystem.foundation.Spacing.dp56
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.designsystem.theme.FacebookLoginButton
import com.example.designsystem.theme.NotoSans

@Composable
fun PlanfitSnsLoginButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    iconSize: Dp = dp24,
    text: String? = null,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black
) {
    val snsLoginButtonFontSize = with(LocalDensity.current) {
        snsLoginButtonFontSize.toSp()
    }
    val icon = painterResource(id = iconRes)

    Button(
        modifier = if (text == null) {
            modifier.size(dp56)
        } else {
            modifier.fillMaxWidth()
        },
        shape = RoundedCornerShape(snsLoginButtonRadius),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        // 버튼의 기본 패딩 때문에 아이콘 크기가 변경되지 않음
        // text가 없을 경우 버튼 패딩 제거
        contentPadding = if (text == null) {
            PaddingValues(dp0)
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        if (text == null) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = Color.Unspecified,
            )
        } else {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                        .align(Alignment.CenterStart)
                        .offset(x = (-12).dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(dp12))
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = text,
                    fontSize = snsLoginButtonFontSize,
                    fontFamily = NotoSans,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlanfitSnsLoginButtonPreview() {
    CMCPlanFitCloneTheme {
        Column {
            PlanfitSnsLoginButton(
                text = "카카오로 계속하기",
                iconRes = R.drawable.kakao_symbol,
                onClick = {},
                backgroundColor = Color.Yellow,
                contentColor = Color.Black
            )
            PlanfitSnsLoginButton(
                text = "Apple로 계속하기",
                iconRes = R.drawable.apple_symbol,
                onClick = {},
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
            PlanfitSnsLoginButton(
                iconRes = R.drawable.facebook_symbol,
                onClick = {},
                backgroundColor = FacebookLoginButton,
                iconSize = 32.dp
            )
            PlanfitSnsLoginButton(
                iconRes = R.drawable.google_symbol,
                onClick = {},
                backgroundColor = Color.White,
                iconSize = 32.dp
            )
        }
    }
}