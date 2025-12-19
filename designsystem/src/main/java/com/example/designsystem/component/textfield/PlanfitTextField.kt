package com.example.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.foundation.Spacing
import com.example.designsystem.theme.CMCPlanFitCloneTheme

@Composable
fun PlanfitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    showLeadingIcon: Boolean = true,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.White.copy(alpha = 0.5f)
            )
        },
        leadingIcon = if (showLeadingIcon) {
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "검색",
                    tint = Color.White.copy(alpha = 0.7f)
                )
            }
        } else {
            null
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF3D3F46),
            unfocusedContainerColor = Color(0xFF3D3F46),
            disabledContainerColor = Color(0xFF3D3F46),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(Spacing.dp12),
        singleLine = true
    )
}

@Preview
@Composable
private fun PlanfitTextFieldPreview() {
    CMCPlanFitCloneTheme {
        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(Spacing.dp16)
        ) {
            PlanfitTextField(
                value = text,
                onValueChange = { text = it }
            )
        }
    }
}