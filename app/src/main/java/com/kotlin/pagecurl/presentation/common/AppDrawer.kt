package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredHeight
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.Call
import androidx.ui.material.icons.filled.DateRange
import androidx.ui.material.icons.filled.Face
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Home
import androidx.ui.material.icons.filled.ThumbUp
import androidx.ui.unit.dp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus

@Composable
fun AppDrawer(
    closeDrawer: () -> Unit,
    backStack: BackStack<AppRoute>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        DrawerButton(
            icon = Filled.Home,
            label = "Home",
            isSelected = CurlViewStatus.selectIndex == 0,
            action = {
                CurlViewStatus.selectIndex = 0
                CurlViewStatus.stack.add(0)
                backStack.push(HomeRoute)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Favorite,
            label = "Ranking",
            isSelected = CurlViewStatus.selectIndex == 1,
            action = {
                CurlViewStatus.selectIndex = 1
                CurlViewStatus.stack.add(1)
                backStack.push(Tab1Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.DateRange,
            label = "BooShelf",
            isSelected = CurlViewStatus.selectIndex == 2,
            action = {
                CurlViewStatus.selectIndex = 2
                CurlViewStatus.stack.add(2)
                backStack.push(Tab2Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Call,
            label = "Store",
            isSelected = CurlViewStatus.selectIndex == 3,
            action = {
                CurlViewStatus.selectIndex = 3
                CurlViewStatus.stack.add(3)
                backStack.push(Tab3Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Face,
            label = "MyPage",
            isSelected = CurlViewStatus.selectIndex == 4,
            action = {
                CurlViewStatus.selectIndex = 4
                CurlViewStatus.stack.add(4)
                backStack.push(Tab4Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.ThumbUp,
            label = "Viewer",
            isSelected = CurlViewStatus.selectIndex == 5,
            action = {
                CurlViewStatus.selectIndex = 5
                CurlViewStatus.stack.add(5)
                backStack.push(Tab5Route)
                closeDrawer()
            }
        )
    }
}
