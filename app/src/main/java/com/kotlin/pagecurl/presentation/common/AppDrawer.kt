package com.kotlin.pagecurl.presentation.common

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredHeight
import androidx.ui.unit.dp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.domainobject.model.listDrawerItems
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus

@Composable
fun AppDrawer(
    closeDrawer: () -> Unit,
    backStack: BackStack<AppRoute>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        listDrawerItems.mapIndexed { index, list ->
            DrawerButton(
                icon = list.second,
                label = list.first,
                isSelected = CurlViewStatus.selectIndex == index,
                action = {
                    CurlViewStatus.selectIndex = index
                    CurlViewStatus.stack.add(index)
                    when (index) {
                        0 -> backStack.push(HomeRoute)
                        1 -> backStack.push(Tab1Route)
                        2 -> backStack.push(Tab2Route)
                        3 -> backStack.push(Tab3Route)
                        4 -> backStack.push(Tab4Route)
                        5 -> backStack.push(Tab5Route)
                    }
                    closeDrawer()
                }
            )
        }
    }
}
