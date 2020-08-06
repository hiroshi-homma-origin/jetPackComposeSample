package com.kotlin.pagecurl.presentation.common

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.kotlin.pagecurl.R.drawable
import com.kotlin.pagecurl.domainobject.model.listDrawerItems

@Composable
fun TopAppBarScreen(
    scaffoldState: ScaffoldState,
    screenNumber: Int
) {
    TopAppBar(
        title = { Text(text = listDrawerItems[screenNumber].first) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.width(60.dp),
                onClick = { }
            ) {
                Icon(
                    asset = imageResource(id = drawable.ic_launcher),
                    modifier = Modifier.width(40.dp).gravity(Alignment.CenterHorizontally)
                )
            }
        }
    )
}
