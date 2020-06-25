package com.kotlin.pagecurl.presentation.bookshelf

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredSize
import androidx.ui.material.Divider
import androidx.ui.material.DrawerState.Closed
import androidx.ui.material.DrawerState.Opened
import androidx.ui.material.IconButton
import androidx.ui.material.ListItem
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.R.drawable
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.User
import com.kotlin.pagecurl.domainobject.model.users
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.selectedUser
import com.kotlin.pagecurl.domainobject.state.selectUser
import com.kotlin.pagecurl.presentation.common.AppDrawer
import com.kotlin.pagecurl.presentation.common.BottomNavigationOnlySelectedLabelComponent
import com.mobnetic.compose.sharedelement.SharedElement
import com.mobnetic.compose.sharedelement.SharedElementType
import com.mobnetic.compose.sharedelement.SharedElementType.FROM
import com.mobnetic.compose.sharedelement.SharedElementsRoot

@Composable
fun BookShelfComponent(backStack: BackStack<AppRoute>) {
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
                title = { Text(text = "BookShelf") },
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
            SharedElementsRoot {
                when (selectedUser) {
                    users[users.lastIndex] -> UsersListScreen(users)
                    else -> {
                        UserDetailsScreen(selectedUser)
                    }
                }
            }
        }
    )
}

@Composable
fun UsersListScreen(users: List<User>) {
    VerticalScroller {
        users.mapIndexed { index, user ->
            ListItem(
                icon = {
                    SharedElement(tag = user, type = FROM) {
                        if (user.avatar > 0) {
                            Image(
                                asset = vectorResource(id = user.avatar),
                                modifier = Modifier.preferredSize(48.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                },
                text = {
                    SharedElement(tag = user to user.name, type = FROM) {
                        Text(text = "No.$index:${user.name}")
                    }
                },
                onClick = { selectUser(user) }
            )
            Divider()
        }
    }
}

@Composable
fun UserDetailsScreen(user: User) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Clickable(
            onClick = { selectedUser = users[users.lastIndex] },
            modifier = Modifier.preferredSize(200.dp).gravity(Alignment.CenterHorizontally)
        ) {
            SharedElement(tag = user, type = SharedElementType.TO) {
                Image(
                    asset = vectorResource(id = user.avatar),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
        SharedElement(
            tag = user to user.name,
            type = SharedElementType.TO,
            modifier = Modifier.gravity(Alignment.CenterHorizontally)
        ) {
            Text(text = user.name, style = MaterialTheme.typography.h1)
        }
    }
}
