package com.example.presentation.onboarding.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.component.text.PlanfitText
import com.example.designsystem.component.textfield.PlanfitTextField
import com.example.designsystem.foundation.Spacing
import com.example.presentation.onboarding.component.GymListWithBlur
import com.example.presentation.onboarding.data.GymInfo
import com.example.presentation.onboarding.data.getDummyGymList
import com.example.presentation.ui.theme.CMCPlanFitCloneTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen3(
    onNavigateToNextStep: () -> Unit = {},
) {
    var searchText by remember { mutableStateOf("") }
    var debouncedText by remember { mutableStateOf("") }
    var showGymList by remember { mutableStateOf(false) }

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedGym by remember { mutableStateOf<GymInfo?>(null) }
    val bottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(searchText) {
        delay(300)
        debouncedText = searchText
    }

    LaunchedEffect(debouncedText) {
        showGymList = debouncedText.isNotEmpty()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Spacing.dp20,
                    vertical = Spacing.dp12,
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            PlanfitText(
                text = "다니시는 헬스장은 어디인가요?",
                fontSize = Spacing.dp24
            )

            Spacer(modifier = Modifier.height(Spacing.dp12))

            PlanfitText(
                text = "헬스장에 있는 기구에 딱 맞게 추천해 드릴게요",
                fontSize = Spacing.dp16,
            )

            Spacer(modifier = Modifier.height(Spacing.dp12))

            PlanfitTextField(
                value = searchText,
                onValueChange = { newValue ->
                    searchText = newValue
                },
                placeholder = "주소로 검색하면 더 빠르게 찾을 수 있어요!"
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        if (showGymList) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(top = Spacing.dp200)
            ) {
                GymListWithBlur(
                    gyms = getDummyGymList(),
                    onGymClick = { gym ->
                        selectedGym = gym
                        showBottomSheet = true
                    },
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = bottomSheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.dp16),
                    verticalArrangement = Arrangement.spacedBy(Spacing.dp8)
                ) {
                    PlanfitText(
                        text = "아래 헬스장에 다니고 계신게 맞나요?",
                        fontSize = Spacing.dp16,
                    )
                    Spacer(modifier = Modifier.height(Spacing.dp12))

                    PlanfitText(
                        text = selectedGym?.name ?: "",
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
                            text = selectedGym?.roadAddress ?: "",
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
                            text = selectedGym?.zipCode ?: "",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = Spacing.dp12,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(Spacing.dp8),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF282A2E),
                                contentColor = Color(0xFFC6C6C8),
                            ),
                            onClick = {
                                showBottomSheet = false
                            }
                        ) {
                            PlanfitText(
                                text = "다시 검색",
                                color = Color(0xFFC6C6C8),
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.width(Spacing.dp8))
                        Button(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(Spacing.dp8),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF21ECC7),
                                contentColor = Color.Black,
                            ),
                            onClick = {
                                showBottomSheet = false
                                onNavigateToNextStep()
                            }
                        ) {
                            PlanfitText(
                                text = "네, 맞아요",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun OnboardingScreen3Preview() {
    CMCPlanFitCloneTheme {
        OnboardingScreen3()
    }
}