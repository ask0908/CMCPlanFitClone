package com.example.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.foundation.Spacing
import java.util.Calendar

@Composable
fun DatePickerContent(
    onConfirm: (year: Int, month: Int, day: Int) -> Unit,
    onCancel: () -> Unit,
) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    var selectedYear by remember { mutableIntStateOf(currentYear) }
    var selectedMonth by remember { mutableIntStateOf(currentMonth) }
    var selectedDay by remember { mutableIntStateOf(currentDay) }

    val years = (1970..currentYear).toList()
    val months = (1..12).toList()

    // 선택된 연도, 월에 따라 일수 계산
    // 연도나 월이 바뀌었을 때만 재계산
    // getActualMaximum() : 지금 설정된 날짜에 맞는 마지막 날짜 리턴(2월 = 28일, 9월 = 30일)
    val daysInMonth = remember(selectedYear, selectedMonth) {
        Calendar.getInstance().apply {
            set(Calendar.YEAR, selectedYear)
            set(Calendar.MONTH, selectedMonth - 1)
        }.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
    val days = (1..daysInMonth).toList()

    // 선택된 날짜가 해당 월의 최대 일수를 초과하면 날짜 조정
    // 1월 31일 선택 후 2월 선택 -> 2월 28일 or 29일로 변경
    LaunchedEffect(daysInMonth) {
        if (selectedDay > daysInMonth) {
            selectedDay = daysInMonth
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.dp20)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // 연, 월, 일 합해서 선택 영역 설정
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.05f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .align(Alignment.Center)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WheelPicker(
                    items = years,
                    initialIndex = years.indexOf(selectedYear),
                    onItemSelected = { selectedYear = it },
                    modifier = Modifier.weight(1f),
                    label = "년",
                )

                Spacer(modifier = Modifier.width(Spacing.dp8))

                WheelPicker(
                    items = months,
                    initialIndex = months.indexOf(selectedMonth),
                    onItemSelected = { selectedMonth = it },
                    modifier = Modifier.weight(0.7f),
                    label = "월",
                )

                Spacer(modifier = Modifier.width(Spacing.dp8))

                WheelPicker(
                    items = days,
                    initialIndex = days.indexOf(selectedDay).coerceAtLeast(0),
                    onItemSelected = { selectedDay = it },
                    modifier = Modifier.weight(0.7f),
                    label = "일",
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.dp40))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(Spacing.dp12),
            onClick = {
                onConfirm(selectedYear, selectedMonth, selectedDay)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF21ECC7)
            )
        ) {
            Text(
                text = "확인",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(Spacing.dp20))
    }
}

@Composable
fun WheelPicker(
    items: List<Int>,
    initialIndex: Int = 0,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "", // 선택한 연, 월, 일 오른쪽에 붙을 글자(년, 월, 일)
) {
    val itemHeight = 40.dp
    val visibleItemsCount = 5
    val pickerHeight = itemHeight * visibleItemsCount

    // LazyColumn 스크롤 상태 기억, 관리하는 객체 생성
    val listState = rememberLazyListState(
        // initialFirstVisibleItemIndex : 처음 표시될 때 어느 아이템부터 보여줄 건지
        // coerceIn() 써서 유효 범위 안에서만 값이 표시되게 예외 처리
        initialFirstVisibleItemIndex = initialIndex.coerceIn(0, items.size - 1)
    )

    // 중앙 아이템 인덱스 추적
    val centerIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.height / 2

            layoutInfo.visibleItemsInfo.minByOrNull {
                kotlin.math.abs((it.offset + it.size / 2) - viewportCenter)
            }?.index ?: 0
        }
    }

    LaunchedEffect(centerIndex) {
        if (centerIndex in items.indices) {
            onItemSelected(items[centerIndex])
        }
    }

    Box(
        modifier = modifier.height(pickerHeight)
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
            contentPadding = PaddingValues(vertical = pickerHeight / 2 - itemHeight / 2),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.size) { index ->
                val item = items[index]
                val isCenter = index == centerIndex

                // 중앙으로부터의 거리 계산
                val distance = kotlin.math.abs(index - centerIndex)
                val alpha = when (distance) {
                    0 -> 1f
                    1 -> 0.6f
                    2 -> 0.3f
                    else -> 0.15f
                }
                val scale = when (distance) {
                    0 -> 1f
                    1 -> 0.85f
                    else -> 0.7f
                }

                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.toString(),
                            fontSize = (20 * scale).sp,
                            color = Color.White.copy(alpha = alpha),
                            fontWeight = if (isCenter) FontWeight.Bold else FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                        if (isCenter && label.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = label,
                                fontSize = (16 * scale).sp,
                                color = Color.White.copy(alpha = alpha * 0.7f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}