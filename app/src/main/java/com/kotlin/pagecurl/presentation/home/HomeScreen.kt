package com.kotlin.pagecurl.presentation.home

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.remember
import androidx.lifecycle.LiveData
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
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
import com.kotlin.pagecurl.R
import com.kotlin.pagecurl.domainobject.model.Person
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.currentScreen
import com.kotlin.pagecurl.viewExt.extcompose.AppDrawer
import com.kotlin.pagecurl.viewExt.extcompose.BottomNavigationOnlySelectedLabelComponent
import com.kotlin.pagecurl.viewExt.extcompose.NetworkImageComponentGlide
import timber.log.Timber

@Composable
fun HomeViewComponent(
    homeViewModel: HomeViewModel,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = currentScreen,
                closeDrawer = { scaffoldState.drawerState = Closed }
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
            BottomNavigationOnlySelectedLabelComponent()
        },
        bodyContent = {
            LiveDataComponent(homeViewModel.superheroes, homeViewModel)
        }
    )
}

@Composable
fun LiveDataComponent(
    superheroesLiveData: LiveData<List<Person>>,
    homeViewModel: HomeViewModel
) {
    val superheroesList by superheroesLiveData.observeAsState(initial = emptyList())
    Timber.d("superheroesList")
    if (superheroesList.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        LiveDataComponentList(superheroesList, homeViewModel)
    }
}

@Composable
fun LiveDataComponentList(
    superheroesList: List<Person>,
    homeViewModel: HomeViewModel
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
fun ChildrenCompose(personList: List<Person>) {
    VerticalScroller(
//        scrollerPosition = ScrollerPosition(1200.0f)
    ) {
        Timber.d("check_scroll_position")
        personList.mapIndexed { index, person ->
            Card(
                shape = RoundedCornerShape(4.dp), color = Color.White,
                modifier = Modifier.fillMaxWidth() + Modifier.padding(8.dp)
            ) {
                ListItem(
                    text = {
                        Text(
                            text = "${index + 1}:${person.name?.nameString}",
                            style = TextStyle(
                                fontFamily = FontFamily.Serif, fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    secondaryText = {
                        Text(
                            text = "Email: ${person.age}",
                            style = TextStyle(
                                fontFamily = FontFamily.Serif, fontSize = 15.sp,
                                fontWeight = FontWeight.Light, color = Color.DarkGray
                            )
                        )
                    },
                    icon = {
                        person.profilePictureUrl?.urlString.let { imageUrl ->
                            Timber.d("checkString:$imageUrl")
                            NetworkImageComponentGlide(
                                url = imageUrl.toString(),
                                modifier = Modifier.preferredWidth(80.dp) + Modifier.preferredHeight
                                (80.dp)
                            )
                        }
                    }
                )
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
