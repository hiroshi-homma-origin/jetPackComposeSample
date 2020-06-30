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
    var offsetxHome: Float = 0f
    var offsetyHome: Float = 0f
    var offsetxRanking: Float = 0f
    var offsetyRanking: Float = 0f
    var offsetxBookShelf: Float = 0f
    var offsetyBookShelf: Float = 0f
    var offsetxStore: Float = 0f
    var offsetyStore: Float = 0f
    var offsetxMyPage: Float = 0f
    var offsetyMyPage: Float = 0f
}

fun setScrollHomeOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetxHome = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetyHome < differenceOffset) {
        CurlViewStatus.offsetyHome = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetyHome = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollRankingOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetxRanking = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetyRanking < differenceOffset) {
        CurlViewStatus.offsetyRanking = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetyRanking = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollStoreOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetxStore = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetyStore < differenceOffset) {
        CurlViewStatus.offsetyStore = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetyStore = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollMyPageOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetxMyPage = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetyMyPage < differenceOffset) {
        CurlViewStatus.offsetyMyPage = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetyMyPage = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun setScrollBookShelfOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetxBookShelf = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsetyBookShelf < differenceOffset) {
        CurlViewStatus.offsetyBookShelf = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsetyBookShelf = it.globalPosition.y.value * -1 + differenceOffset
    }
}

fun selectUser(selectUser: User) {
    selectedUser = selectUser
}
