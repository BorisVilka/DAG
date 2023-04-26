package com.advertisment.advertisementboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import com.advertisment.advertisementboard.domain.model.NavDrawerItem
import com.advertisment.advertismentboard.presentation.ui.theme.Green
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceNormal
import com.advertisment.advertismentboard.presentation.ui.theme.SpaceSmall

@Composable
fun DrawerItem(
    item: NavDrawerItem,
    selected: Boolean,
    onItemClick: (NavDrawerItem) -> Unit
) {

    val backgroundColor = if (selected) Green else MaterialTheme.colors.surface
    val contentColor = if(selected) Color.White else Color.Black

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .padding(vertical = SpaceNormal, horizontal = SpaceSmall)
    ) {
        Image(
            imageVector = item.icon,
            contentDescription = stringResource(id = item.title),
            colorFilter = ColorFilter.tint(contentColor)
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        Text(
            text = stringResource(id = item.title),
            color = contentColor
        )
    }
}