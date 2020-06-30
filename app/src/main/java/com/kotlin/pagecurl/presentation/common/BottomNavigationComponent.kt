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
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.domainobject.model.listItems
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomNavigationOnlySelectedLabelComponent(
    backStack: BackStack<AppRoute>
) {
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
                            fontSize = 10.sp
                        )
                    )
                },
                selected = CurlViewStatus.selectIndex == index,
                onSelected = {
                    CurlViewStatus.selectIndex = index
                    CurlViewStatus.stack.add(index)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(255)
                        when (index) {
                            0 -> backStack.push(HomeRoute)
                            1 -> backStack.push(Tab1Route)
                            2 -> backStack.push(Tab2Route)
                            3 -> backStack.push(Tab3Route)
                            4 -> backStack.push(Tab4Route)
                            5 -> backStack.push(Tab5Route)
                        }
                    }
                },
                alwaysShowLabels = false
            )
        }
    }
}
