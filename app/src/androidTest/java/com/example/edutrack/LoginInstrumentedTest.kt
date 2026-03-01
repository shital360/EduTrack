package com.example.edutrack

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.Intents.intended
import com.example.edutrack.View.LoginActivity
import com.example.edutrack.View.DashboardActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<LoginActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testLoginNavigatesToDashboard() {

        // Enter Email
        composeRule.onNodeWithTag("email")
            .assertIsDisplayed()
            .performTextInput("shital@gmail.com")

        // Enter Password
        composeRule.onNodeWithTag("password")
            .assertIsDisplayed()
            .performTextInput("shital123")

        // Click Login Button
        composeRule.onNodeWithTag("login")
            .assertIsDisplayed()
            .performClick()

        // WAIT for Firebase async call to complete
        composeRule.waitForIdle()
        Thread.sleep(4000)   // Important because Firebase is async

        // Verify navigation happened
        intended(hasComponent(DashboardActivity::class.java.name))
    }
}