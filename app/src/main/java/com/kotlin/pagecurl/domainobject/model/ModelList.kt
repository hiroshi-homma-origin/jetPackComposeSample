package com.kotlin.pagecurl.domainobject.model

import androidx.annotation.DrawableRes
import androidx.ui.graphics.Color
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.Android
import androidx.ui.material.icons.filled.Call
import androidx.ui.material.icons.filled.DateRange
import androidx.ui.material.icons.filled.Face
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Home
import com.kotlin.pagecurl.R

var pokeListSizeFirst = 20
var userListSizeFirst = 16

enum class AppScreen(val displayNameString: String) {
    Screen1("ホーム"),
    Screen2("ランキング"),
    Screen3("本棚"),
    Screen4("ストア"),
    Screen5("マイページ"),
    Screen6("ビューワー")
}

val listItems = listOf(
    Pair(AppScreen.Screen1.displayNameString, Filled.Home),
    Pair(AppScreen.Screen2.displayNameString, Filled.Favorite),
    Pair(AppScreen.Screen3.displayNameString, Filled.DateRange),
    Pair(AppScreen.Screen4.displayNameString, Filled.Call),
    Pair(AppScreen.Screen5.displayNameString, Filled.Face)
)
val listDrawerItems = listOf(
    Pair(AppScreen.Screen1.displayNameString, Filled.Home),
    Pair(AppScreen.Screen2.displayNameString, Filled.Favorite),
    Pair(AppScreen.Screen3.displayNameString, Filled.DateRange),
    Pair(AppScreen.Screen4.displayNameString, Filled.Call),
    Pair(AppScreen.Screen5.displayNameString, Filled.Face),
    Pair(AppScreen.Screen6.displayNameString, Filled.Android)
)

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

data class User(val id: Int, @DrawableRes val avatar: Int, val name: String)

val users = listOf(
    User(1, R.drawable.avatar_1, "Adam"),
    User(2, R.drawable.avatar_2, "Andrew"),
    User(3, R.drawable.avatar_3, "Anna"),
    User(4, R.drawable.avatar_4, "Boris"),
    User(5, R.drawable.avatar_5, "Carl"),
    User(6, R.drawable.avatar_6, "Donna"),
    User(7, R.drawable.avatar_7, "Emily"),
    User(8, R.drawable.avatar_8, "Fiona"),
    User(9, R.drawable.avatar_9, "Grace"),
    User(10, R.drawable.avatar_10, "Irene"),
    User(11, R.drawable.avatar_11, "Jack"),
    User(12, R.drawable.avatar_12, "Jake"),
    User(13, R.drawable.avatar_13, "Mary"),
    User(14, R.drawable.avatar_14, "Peter"),
    User(15, R.drawable.avatar_15, "Rose"),
    User(16, R.drawable.avatar_16, "Victor"),
    User(17, R.drawable.avatar_17, "Adam"),
    User(18, R.drawable.avatar_18, "Andrew"),
    User(19, R.drawable.avatar_19, "Anna"),
    User(20, R.drawable.avatar_20, "Boris"),
    User(21, R.drawable.avatar_21, "Carl"),
    User(22, R.drawable.avatar_22, "Donna"),
    User(23, R.drawable.avatar_23, "Emily"),
    User(24, R.drawable.avatar_24, "Fiona"),
    User(25, R.drawable.avatar_25, "Grace"),
    User(26, R.drawable.avatar_26, "Irene"),
    User(27, R.drawable.avatar_27, "Jack"),
    User(28, R.drawable.avatar_28, "Jake"),
    User(29, R.drawable.avatar_29, "Mary"),
    User(30, R.drawable.avatar_30, "Peter"),
    User(31, R.drawable.avatar_31, "Rose"),
    User(32, R.drawable.avatar_32, "Victor"),
    User(33, 0, "")
)

sealed class AppRoute {
    object HomeRoute : AppRoute() {
        override fun toString(): String = AppScreen.Screen1.name
    }

    object Tab1Route : AppRoute() {
        override fun toString(): String = AppScreen.Screen2.name
    }

    object Tab2Route : AppRoute() {
        override fun toString(): String = AppScreen.Screen3.name
    }

    object Tab3Route : AppRoute() {
        override fun toString(): String = AppScreen.Screen4.name
    }

    object Tab4Route : AppRoute() {
        override fun toString(): String = AppScreen.Screen5.name
    }

    object Tab5Route : AppRoute() {
        override fun toString(): String = AppScreen.Screen6.name
    }
}

val mBitmapIds = intArrayOf(
    R.drawable.kimetsu1, R.drawable.kimetsu2, R.drawable.kimetsu3, R.drawable.kimetsu4,
    R.drawable.kimetsu5, R.drawable.kimetsu6, R.drawable.kimetsu7, R.drawable.kimetsu8,
    R.drawable.kimetsu7, R.drawable.kimetsu6, R.drawable.kimetsu5, R.drawable.kimetsu4,
    R.drawable.kimetsu3, R.drawable.kimetsu2, R.drawable.kimetsu1, R.drawable.kimetsu2,
    R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
    R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
    R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
    R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
    R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
    R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
    R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
    R.drawable.kimetsu5, R.drawable.kimetsu4, R.drawable.kimetsu3, R.drawable.kimetsu2,
    R.drawable.kimetsu1
)
