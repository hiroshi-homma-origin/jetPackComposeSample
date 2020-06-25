package com.kotlin.pagecurl.domainobject.model

import androidx.annotation.DrawableRes
import androidx.ui.graphics.Color
import com.kotlin.pagecurl.R

enum class AppScreen(val displayNameString: String) {
    Screen1("ホーム"),
    Screen2("ランキング"),
    Screen3("本棚"),
    Screen4("ストア"),
    Screen5("マイページ"),
    Screen6("ビューワー")
}

val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

data class User(@DrawableRes val avatar: Int, val name: String)

val users = listOf(
    User(R.drawable.avatar_1, "Adam"),
    User(R.drawable.avatar_2, "Andrew"),
    User(R.drawable.avatar_3, "Anna"),
    User(R.drawable.avatar_4, "Boris"),
    User(R.drawable.avatar_5, "Carl"),
    User(R.drawable.avatar_6, "Donna"),
    User(R.drawable.avatar_7, "Emily"),
    User(R.drawable.avatar_8, "Fiona"),
    User(R.drawable.avatar_9, "Grace"),
    User(R.drawable.avatar_10, "Irene"),
    User(R.drawable.avatar_11, "Jack"),
    User(R.drawable.avatar_12, "Jake"),
    User(R.drawable.avatar_13, "Mary"),
    User(R.drawable.avatar_14, "Peter"),
    User(R.drawable.avatar_15, "Rose"),
    User(R.drawable.avatar_16, "Victor")
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
