package com.kotlin.pagecurl.domainobject.state

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.core.LayoutCoordinates
import androidx.ui.core.globalPosition
import com.kotlin.pagecurl.domainobject.model.AppScreen

object CurlViewStatus {
    var stack = mutableListOf(0)
    var currentScreen by mutableStateOf(AppScreen.Screen1)
    var selectIndex by mutableStateOf(AppScreen.Screen1.ordinal)
    var offsetx: Float = 0f
    var offsety: Float = 0f
}

fun setScrollOffset(it: LayoutCoordinates) {
    CurlViewStatus.offsetx = it.globalPosition.x.value * -1
    if (CurlViewStatus.offsety < 221.0f) {
        CurlViewStatus.offsety = it.globalPosition.y.value * -1
    } else {
        CurlViewStatus.offsety = it.globalPosition.y.value * -1 + 220.0f
    }
}
