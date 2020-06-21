package com.kotlin.pagecurl

import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.ui.test.assertIsDisplayed
import androidx.ui.test.createComposeRule
import androidx.ui.test.findByText
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen2
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@MediumTest
@RunWith(JUnit4::class)
class CurlViewUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // Using targetContext as the Context of the instrumentation code
        composeTestRule.launchCurlViewApp(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun app_launches() {
        findByText(Screen2.displayNameString).assertIsDisplayed()
    }
}
