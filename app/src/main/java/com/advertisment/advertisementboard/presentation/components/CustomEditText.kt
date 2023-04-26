package com.advertisment.advertisementboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.advertisment.advertismentboard.presentation.ui.theme.Green
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertismentboard.presentation.ui.theme.White

@Composable
fun CustomEditText(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    hintText: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    Column {

        Text(
            text = labelText,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .border(width = 2.dp, color = Green, shape = MaterialTheme.shapes.medium)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.caption,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                modifier = modifier
                    .background(color = White)
                    .padding(SpaceNormal)
                    .fillMaxWidth(0.8f)
            )

            if (value.isEmpty()) {
                Text(
                    text = hintText,
                    style = MaterialTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(SpaceNormal)
                )
            }
        }
    }
}