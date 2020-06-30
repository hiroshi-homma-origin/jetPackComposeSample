package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.layout.width
import androidx.ui.material.DrawerState.Opened
import androidx.ui.material.IconButton
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.kotlin.pagecurl.R.drawable
import com.kotlin.pagecurl.domainobject.model.listItems

@Composable
fun TopAppBarScreen(
    scaffoldState: ScaffoldState,
    screenNumber: Int
) {
    TopAppBar(
        title = { Text(text = listItems.get(screenNumber).first) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.width(60.dp),
                onClick = { scaffoldState.drawerState = Opened }
            ) {
                Icon(
                    asset = imageResource(id = drawable.ic_launcher),
                    modifier = Modifier.width(40.dp).gravity(Alignment.CenterHorizontally)
                )
            }
        }
    )
}
