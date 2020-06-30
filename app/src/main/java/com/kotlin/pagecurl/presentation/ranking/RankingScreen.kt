package com.kotlin.pagecurl.presentation.ranking

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.core.globalPosition
import androidx.ui.core.onChildPositioned
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.material.DrawerState.Closed
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.unit.dp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.setScrollRankingOffset
import com.kotlin.pagecurl.presentation.common.AppDrawer
import com.kotlin.pagecurl.presentation.common.BottomNavigationOnlySelectedLabelComponent
import com.kotlin.pagecurl.presentation.common.TopAppBarScreen
import timber.log.Timber

@Composable
fun RankingComponent(backStack: BackStack<AppRoute>) {
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
            TopAppBarScreen(scaffoldState, 1)
        },
        bottomAppBar = {
            BottomNavigationOnlySelectedLabelComponent(backStack)
        },
        bodyContent = { modifier ->
            VerticalScroller(
                modifier = Modifier.onChildPositioned {
                    Timber.d("check_condition1:${it.globalPosition}")
                    setScrollRankingOffset(it)
                },
                scrollerPosition = ScrollerPosition(CurlViewStatus.offsetyRanking)
            ) {
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
