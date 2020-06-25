package com.kotlin.pagecurl

import android.content.Context
import androidx.compose.Composable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.test.ComposeTestRule
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen2
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.testCompose.RootScreenViewTest

fun ComposeTestRule.launchCurlViewApp(context: Context) {
    setContent {
        CurlViewStatus.resetState()
        RootScreenViewTest()
    }
}

fun CurlViewStatus.resetState() {
    currentScreen = Screen2
}

fun ComposeTestRule.setMaterialContent(children: @Composable() () -> Unit) {
    setContent {
        MaterialTheme {
            Surface {
                children()
            }
        }
    }
}
