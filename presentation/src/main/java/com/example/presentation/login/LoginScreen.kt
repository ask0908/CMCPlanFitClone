package com.example.presentation.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.foundation.Spacing.dp24
import com.example.designsystem.theme.CMCPlanFitCloneTheme
import com.example.presentation.login.component.LoginTermsSection
import com.example.presentation.login.component.SnsLoginButtons
import com.example.presentation.login.component.WelcomeTitles
import com.example.presentation.login.constant.LoginConstants

private const val TOP_TITLE_WEIGHT = 0.48f
private const val LOGIN_BUTTON_BOTTOM_WEIGHT = 0.4f

@Composable
fun LoginScreen(
    onNavigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    CMCPlanFitCloneTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PlanfitLoginBackground {
                Column(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(dp24),
                ) {
                    WelcomeTitles()
                    Spacer(modifier = Modifier.fillMaxHeight(TOP_TITLE_WEIGHT))

                    SnsLoginButtons {
                        onNavigateToOnboarding()
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(LOGIN_BUTTON_BOTTOM_WEIGHT))

                    LoginTermsSection()
                }
            }
        }
    }
}

@Composable
fun PlanfitLoginBackground(
    topColor: Color = LoginConstants.CURVE_TOP_COLOR,
    bottomColor: Color = LoginConstants.CURVE_BOTTOM_COLOR,
    content: @Composable () -> Unit = {},
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Canvas에서 나중에 그린 것은 위에 덮어씌워지기 때문에 하단 회색 영역을 먼저 그림
        drawRect(color = bottomColor)

        val curve = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height * 0.2f)

            drawRightToCenterCurve(this@apply, this@Canvas)
            drawCenterToLeftCurve(this@apply, this@Canvas)

            lineTo(0f, 0f)
            close()
        }

        drawPath(path = curve, color = topColor)
    }

    content()
}

private fun drawRightToCenterCurve(
    path: Path,
    scope: DrawScope
) {
    path.cubicTo(
        scope.size.width * 0.65f, scope.size.height * 0.25f,
        scope.size.width * 0.558f, scope.size.height * 0.35f,
        scope.size.width * 0.5f, scope.size.height * 0.40f
    )
}

private fun drawCenterToLeftCurve(
    path: Path,
    scope: DrawScope
) {
    path.cubicTo(
        scope.size.width * 0.45f, scope.size.height * 0.45f,
        scope.size.width * 0.25f, scope.size.height * 0.55f,
        0f, scope.size.height * 0.55f
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    CMCPlanFitCloneTheme {
        LoginScreen(
            onNavigateToOnboarding = {},
        )
    }
}