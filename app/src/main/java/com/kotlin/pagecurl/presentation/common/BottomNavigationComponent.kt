package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.material.BottomNavigation
import androidx.ui.material.BottomNavigationItem
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.kotlin.pagecurl.domainobject.model.listItems
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus

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
                text = {
                    Text(
                        text = list.first,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 8.sp
                        )
                    )
                },
                selected = CurlViewStatus.selectIndex == index,
                onSelected = {
                },
                alwaysShowLabels = false
            )
        }
    }
}
