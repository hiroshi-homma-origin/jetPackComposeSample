package com.kotlin.pagecurl.presentation.bookshelf

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.boundsInParent
import androidx.ui.core.boundsInRoot
import androidx.ui.core.onChildPositioned
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Image
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.material.Divider
import androidx.ui.material.ListItem
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.User
import com.kotlin.pagecurl.domainobject.model.users
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.selectedUser
import com.kotlin.pagecurl.domainobject.state.selectUser
import com.kotlin.pagecurl.domainobject.state.setScrollBookShelfOffset
import com.kotlin.pagecurl.viewExt.custom.CustomIconButton
import com.kotlin.pagecurl.viewModel.BookShelfViewModel
import com.mobnetic.compose.sharedelement.SharedElement
import com.mobnetic.compose.sharedelement.SharedElementType
import com.mobnetic.compose.sharedelement.SharedElementType.FROM
import com.mobnetic.compose.sharedelement.SharedElementsRoot
import timber.log.Timber

@Composable
fun BookShelfComponent(bookShelfViewModel: BookShelfViewModel) {

//    val uList by bookShelfViewModel.getData().observeAsState(initial = emptyList())
    Surface(modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp), gravity = ContentGravity.Center,
            children = {
                SharedElementsRoot {
                    when (selectedUser) {
                        bookShelfViewModel.getLastIndexData() -> UsersListScreen(bookShelfViewModel.getData().value!!, bookShelfViewModel)
                        else -> UserDetailsScreen(selectedUser)
                    }
                }
            }
        )
    }
}

@Composable
fun UsersListScreen(
    users: List<User>,
    bookShelfViewModel: BookShelfViewModel
) {
    var checkCount = 0
    VerticalScroller(
        modifier = Modifier.onChildPositioned {
            if (it.boundsInParent.bottom < it.boundsInRoot.bottom) {
                checkCount++
                if (checkCount == 1) {
                    if (CurlViewStatus.userListSize < 32) CurlViewStatus.userListSize += 17
                    Timber.d("check_condition3:${CurlViewStatus.userListSize}")
                }
            }
            setScrollBookShelfOffset(it)
        },
        scrollerPosition = ScrollerPosition(CurlViewStatus.offsetYBookShelf)
    ) {
        users.mapIndexed { index, user ->
            ListItem(
                icon = {
                    SharedElement(tag = user, type = FROM) {
                        if (user.avatar > 0) {
                            Image(
                                asset = vectorResource(id = user.avatar),
                                modifier = Modifier.preferredSize(60.dp),
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
        SharedElement(
            tag = user,
            modifier = Modifier.fillMaxWidth(),
            type = SharedElementType.TO
        ) {
            CustomIconButton(
                onClick = { selectedUser = users[users.lastIndex] },
                modifier = Modifier.preferredSize(320.dp).gravity(Alignment.CenterHorizontally),
                avatar = user.avatar
            )
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
