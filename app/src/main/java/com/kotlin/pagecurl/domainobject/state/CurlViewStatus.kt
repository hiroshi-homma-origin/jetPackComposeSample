package com.kotlin.pagecurl.domainobject.state

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.core.LayoutCoordinates
import androidx.ui.core.globalPosition
import com.kotlin.pagecurl.domainobject.model.AppScreen
import com.kotlin.pagecurl.domainobject.model.User
import com.kotlin.pagecurl.domainobject.model.users
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.selectedUser

object CurlViewStatus {
    var stack = mutableListOf(0)
    var currentScreen by mutableStateOf(AppScreen.Screen1)
    var selectIndex by mutableStateOf(AppScreen.Screen1.ordinal)
    var selectedUser by mutableStateOf(users[users.lastIndex])
    var offsetXHome: Float = 0f
    var offsetYHome: Float = 0f
    var offsetXRanking: Float = 0f
    var offsetYRanking: Float = 0f
    var offsetXBookShelf: Float = 0f
    var offsetYBookShelf: Float = 0f
    var offsetXStore: Float = 0f
    var offsetYStore: Float = 0f
    var offsetXMyPage: Float = 0f
    var offsetYMyPage: Float = 0f
}

fun setScrollHomeOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXHome = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetYHome < differenceOffset) {
        CurlViewStatus.offsetYHome = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetYHome = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollRankingOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXRanking = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetYRanking < differenceOffset) {
        CurlViewStatus.offsetYRanking = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetYRanking = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollStoreOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXStore = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetYStore < differenceOffset) {
        CurlViewStatus.offsetYStore = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetYStore = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollMyPageOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXMyPage = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetYMyPage < differenceOffset) {
        CurlViewStatus.offsetYMyPage = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetYMyPage = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollBookShelfOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXBookShelf = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetYBookShelf < differenceOffset) {
        CurlViewStatus.offsetYBookShelf = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetYBookShelf = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun selectUser(selectUser: User) {
    selectedUser = selectUser
}
