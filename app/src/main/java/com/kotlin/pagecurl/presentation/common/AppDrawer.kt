package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredHeight
import androidx.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.listDrawerItems
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus

@Composable
fun AppDrawer(
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        listDrawerItems.mapIndexed { index, list ->
            DrawerButton(
                icon = list.second,
                label = list.first,
                isSelected = CurlViewStatus.selectIndex == index,
                action = {
                    closeDrawer()
                }
            )
        }
    }
}
