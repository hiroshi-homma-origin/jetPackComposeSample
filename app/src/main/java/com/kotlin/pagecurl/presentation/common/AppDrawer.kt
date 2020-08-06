package com.kotlin.pagecurl.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.listDrawerItems

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
                isSelected = false,
                action = {
                    closeDrawer()
                }
            )
        }
    }
}
