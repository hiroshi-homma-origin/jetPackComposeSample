package com.kotlin.pagecurl.presentation.store

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.material.DrawerState.Closed
import androidx.ui.material.DrawerState.Opened
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.R.drawable
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.presentation.common.AppDrawer
import com.kotlin.pagecurl.presentation.common.BottomNavigationOnlySelectedLabelComponent

@Composable
fun StoreComponent(backStack: BackStack<AppRoute>) {
    val scaffoldState = remember { ScaffoldState() }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                closeDrawer = { scaffoldState.drawerState = Closed },
                backStack = backStack
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Store") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = Opened }) {
                        Icon(imageResource(id = drawable.ic_launcher))
                    }
                }
            )
        },
        bottomAppBar = {
            BottomNavigationOnlySelectedLabelComponent(backStack)
        },
        bodyContent = { modifier ->
            VerticalScroller {
                Column(modifier) {
                    repeat(100) {
                        Card(
                            color = colors[it % colors.size],
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Spacer(modifier = Modifier.fillMaxWidth() + Modifier.preferredHeight(200.dp))
                        }
                    }
                }
            }
        }
    )
}
