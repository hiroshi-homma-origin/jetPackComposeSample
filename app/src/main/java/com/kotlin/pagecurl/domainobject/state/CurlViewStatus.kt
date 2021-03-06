package com.kotlin.pagecurl.domainobject.state

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.core.LayoutCoordinates
import androidx.ui.core.globalPosition
import com.kotlin.pagecurl.domainobject.model.AppScreen
import com.kotlin.pagecurl.domainobject.model.User
import com.kotlin.pagecurl.domainobject.model.pokeListSizeFirst
import com.kotlin.pagecurl.domainobject.model.userListSizeFirst
import com.kotlin.pagecurl.domainobject.model.users
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.selectedUser

object CurlViewStatus {
    var stack = mutableListOf(0)
    var currentScreen by mutableStateOf(AppScreen.Screen1)
    var selectIndex by mutableStateOf(AppScreen.Screen1.ordinal)
    var selectedUser by mutableStateOf(users[users.lastIndex])
    var pokeListSize by mutableStateOf(pokeListSizeFirst)
    var userListSize by mutableStateOf(userListSizeFirst)
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
    CurlViewStatus.offsetXHome = it.globalPosition.x * -1
    if (CurlViewStatus.offsetYHome < differenceOffset) {
        CurlViewStatus.offsetYHome = it.globalPosition.y * -1
    } else {
        CurlViewStatus.offsetYHome = it.globalPosition.y * -1 + differenceOffset
    }
}

fun setScrollRankingOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXRanking = it.globalPosition.x * -1
    if (CurlViewStatus.offsetYRanking < differenceOffset) {
        CurlViewStatus.offsetYRanking = it.globalPosition.y * -1
    } else {
        CurlViewStatus.offsetYRanking = it.globalPosition.y * -1 + differenceOffset
    }
}

fun setScrollStoreOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXStore = it.globalPosition.x * -1
    if (CurlViewStatus.offsetYStore < differenceOffset) {
        CurlViewStatus.offsetYStore = it.globalPosition.y * -1
    } else {
        CurlViewStatus.offsetYStore = it.globalPosition.y * -1 + differenceOffset
    }
}

fun setScrollMyPageOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXMyPage = it.globalPosition.x * -1
    if (CurlViewStatus.offsetYMyPage < differenceOffset) {
        CurlViewStatus.offsetYMyPage = it.globalPosition.y * -1
    } else {
        CurlViewStatus.offsetYMyPage = it.globalPosition.y * -1 + differenceOffset
    }
}

fun setScrollBookShelfOffset(it: LayoutCoordinates) {
    val differenceOffset = 221.0f
    CurlViewStatus.offsetXBookShelf = it.globalPosition.x * -1
    if (CurlViewStatus.offsetYBookShelf < differenceOffset) {
        CurlViewStatus.offsetYBookShelf = it.globalPosition.y * -1
    } else {
        CurlViewStatus.offsetYBookShelf = it.globalPosition.y * -1 + differenceOffset
    }
}

fun setAllResetOffset() {
    CurlViewStatus.offsetXHome = 0f
    CurlViewStatus.offsetYHome = 0f
    CurlViewStatus.offsetXRanking = 0f
    CurlViewStatus.offsetYRanking = 0f
    CurlViewStatus.offsetXBookShelf = 0f
    CurlViewStatus.offsetYBookShelf = 0f
    CurlViewStatus.offsetXStore = 0f
    CurlViewStatus.offsetYStore = 0f
    CurlViewStatus.offsetXMyPage = 0f
    CurlViewStatus.offsetYMyPage = 0f
}

fun selectUser(selectUser: User) {
    selectedUser = selectUser
}
