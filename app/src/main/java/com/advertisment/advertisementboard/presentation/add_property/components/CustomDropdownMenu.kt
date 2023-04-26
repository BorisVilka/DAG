package com.advertisment.advertisementboard.presentation.add_property.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.advertisment.advertismentboard.presentation.ui.theme.Green
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall
import com.advertisment.advertismentboard.presentation.ui.theme.White

@Composable
fun CustomDropdownMenu(
    items: List<String>,
    labelText: String,
    selectedItem: String,
    isShowing: Boolean,
    onMenuClick: () -> Unit,
    onItemClick: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column {

        Text(
            text = labelText,
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

        Box(
            modifier = modifier
        ) {
            DropdownMenuItem(
                onClick = {
                    onMenuClick()
                },
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = White)
                    .border(width = 2.dp, color = Green, shape = MaterialTheme.shapes.medium)
            ) {
                Text(
                    text = selectedItem,
                    style = MaterialTheme.typography.caption
                )
            }

            DropdownMenu(
                expanded = isShowing,
                onDismissRequest = {
                    onDismissRequest()
                }
            ) {
                items.forEach { city ->
                    DropdownMenuItem(
                        onClick = { onItemClick(city) }
                    ) {
                        Text(text = city)
                    }
                }
            }
        }
    }
}