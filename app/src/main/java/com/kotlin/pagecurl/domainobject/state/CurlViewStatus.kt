package com.kotlin.pagecurl.domainobject.state

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import com.kotlin.pagecurl.domainobject.model.AppScreen

object CurlViewStatus {
    var stack = mutableListOf(0)
    var currentScreen by mutableStateOf(AppScreen.Screen1)
    var selectIndex by mutableStateOf(AppScreen.Screen1.ordinal)
    var offsetx: Float = 0f
    var offsety: Float = 0f
}
