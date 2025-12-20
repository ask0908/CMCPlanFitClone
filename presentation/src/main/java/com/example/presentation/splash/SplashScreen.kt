package com.example.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.designsystem.theme.NotoSans
import com.example.designsystem.theme.SplashBackground
import com.example.designsystem.theme.SplashMintText
import com.example.presentation.R
import kotlinx.coroutines.delay

/**
 * 스플래시 화면
 *
 * @param onNavigateToLogin 로그인 화면으로 이동
 */
@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
) {
    SplashContent()

    LaunchedEffect(Unit) {
        delay(2000L)
        onNavigateToLogin()
    }
}

@Composable
private fun SplashContent() {
    // sp를 쓰면 시스템 글자 크기에 영향을 받아서 dp를 sp로 변환
    val splashTitleFontSize = with(LocalDensity.current) {
        Spacing.dp48.toSp()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashBackground)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = Spacing.dp200),
            text = stringResource(R.string.splashTitle),
            fontSize = splashTitleFontSize,
            fontWeight = FontWeight.Bold,
            fontFamily = NotoSans,
            color = SplashMintText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    CMCPlanFitCloneTheme {
        SplashScreen(
            onNavigateToLogin = {},
        )
    }
}