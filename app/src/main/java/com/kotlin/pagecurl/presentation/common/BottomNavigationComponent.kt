package com.kotlin.pagecurl.presentation.common

import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.listItems

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    BottomNavigation(
        modifier = Modifier.padding(0.dp)
    ) {
        listItems.mapIndexed { index, list ->
            BottomNavigationItem(
                icon = {
                    Icon(asset = list.second)
                },
//                text = {
//                    Text(
//                        text = list.first,
//                        style = TextStyle(
//                            fontFamily = FontFamily.Monospace,
//                            fontSize = 8.sp
//                        )
//                    )
//                },
                selected = false,
//                onSelected = {},
                alwaysShowLabels = false,
                onSelect = {}
            )
        }
    }
}
