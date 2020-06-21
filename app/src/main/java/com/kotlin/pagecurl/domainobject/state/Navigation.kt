package com.kotlin.pagecurl.domainobject.state

import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import com.kotlin.pagecurl.domainobject.model.AppScreen

object CurlViewStatus {
    var currentScreen by mutableStateOf(AppScreen.Screen1)
    var selectIndex by mutableStateOf(AppScreen.Screen1.ordinal)
}

fun navigateTo(destination: AppScreen) {
    CurlViewStatus.currentScreen = destination
    CurlViewStatus.selectIndex = destination.ordinal
}
