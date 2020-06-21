package com.kotlin.pagecurl.presentation.mypage

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
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.kotlin.pagecurl.R.drawable
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.viewExt.extcompose.AppDrawer
import com.kotlin.pagecurl.viewExt.extcompose.BottomNavigationOnlySelectedLabelComponent

@Composable
fun MyPageComponent() {
    val scaffoldState = remember { ScaffoldState() }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = CurlViewStatus.currentScreen,
                closeDrawer = { scaffoldState.drawerState = Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "MyPage") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = Opened }) {
                        Icon(imageResource(id = drawable.ic_launcher))
                    }
                }
            )
        },
        bottomAppBar = {
            BottomNavigationOnlySelectedLabelComponent()
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

@Preview
@Composable
fun PreviewMyPageComponent() {
    MyPageComponent()
}
