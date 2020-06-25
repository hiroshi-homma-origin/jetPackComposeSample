package com.kotlin.pagecurl.presentation.home

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.globalPosition
import androidx.ui.core.onChildPositioned
import androidx.ui.core.onPositioned
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement.Center
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.layout.wrapContentWidth
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Card
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.DrawerState.Closed
import androidx.ui.material.DrawerState.Opened
import androidx.ui.material.IconButton
import androidx.ui.material.ListItem
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.Surface
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.MyLifecycleOwner
import com.kotlin.pagecurl.R
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.currentScreen
import com.kotlin.pagecurl.viewExt.extcompose.AppDrawer
import com.kotlin.pagecurl.viewExt.extcompose.BottomNavigationOnlySelectedLabelComponent
import com.kotlin.pagecurl.viewExt.extcompose.NetworkImageComponentGlide
import org.jetbrains.annotations.Nullable
import timber.log.Timber

private val myLifecycleOwner = MyLifecycleOwner()

@Composable
fun HomeViewComponent(
    homeViewModel: HomeViewModel,
    backStack: BackStack<AppRoute>,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = currentScreen,
                closeDrawer = { scaffoldState.drawerState = Closed },
                backStack = backStack
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = Opened }) {
                        Icon(imageResource(id = R.drawable.ic_launcher))
                    }
                }
            )
        },
        bottomAppBar = {
            BottomNavigationOnlySelectedLabelComponent(backStack)
        },
        bodyContent = {
            LiveDataComponent(homeViewModel)
        }
    )
}

@Composable
fun LiveDataComponent(
    homeViewModel: HomeViewModel
) {
    val pList by homeViewModel.pokemonLiveData.observeAsState(initial = emptyList())
    Timber.d("pokemonsList3:$pList")
    if (pList.isNotEmpty()) {
        LiveDataComponentList(pList as MutableList<Pokemon>)
    } else {
        LiveDataLoadingComponent()
    }
}

@Composable
fun LiveDataComponentList(
    superheroesList: MutableList<Pokemon>
) {
    Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp), gravity = ContentGravity.Center,
            children = {
                ChildrenCompose(superheroesList)
            }
        )
    }
}

@Composable
fun ChildrenCompose(personList: @Nullable MutableList<Pokemon>) {
    Timber.d("check_condition0:${CurlViewStatus.offsety}")
    VerticalScroller(
        modifier = Modifier.onChildPositioned {
            Timber.d("check_condition1:${it.globalPosition}")
            if (it.globalPosition.x.value < 221.0f) {
                CurlViewStatus.offsetx = it.globalPosition.x.value * -1
            } else {
                CurlViewStatus.offsetx = it.globalPosition.x.value * -1
            }
            if (CurlViewStatus.offsety < 221.0f) {
                CurlViewStatus.offsety = it.globalPosition.y.value * -1
            } else {
                CurlViewStatus.offsety = it.globalPosition.y.value * -1 + 220.0f
            }
        },
        scrollerPosition = ScrollerPosition(CurlViewStatus.offsety)
    ) {
        Column(
            modifier = Modifier.onPositioned {
                Timber.d("check_condition4:${it.globalPosition}")
            }
        ) {
            personList.mapIndexed { index, pokemon ->
                Card(
                    color = colors[index % colors.size],
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp) +
                        Modifier.fillMaxWidth() +
                        Modifier.preferredHeight(100.dp)
                ) {
                    ListItem(
                        text = {
                            Text(
                                text = "${index + 1}:${pokemon.name()}",
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif, fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        },
                        secondaryText = {
                            Text(
                                text = "type: ${pokemon.types()}",
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif, fontSize = 15.sp,
                                    fontWeight = FontWeight.Light, color = Color.DarkGray
                                )
                            )
                        },
                        icon = {
                            Timber.d("checkString:$pokemon.image()")
                            NetworkImageComponentGlide(
                                url = pokemon.image().toString(),
                                modifier = Modifier.preferredWidth(80.dp) + Modifier.preferredHeight
                                (80.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LiveDataLoadingComponent() {
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}
