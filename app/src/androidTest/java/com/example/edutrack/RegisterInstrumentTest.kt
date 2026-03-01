package com.example.edutrack

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.edutrack.View.DashboardActivity
import com.example.edutrack.View.LoginActivity
import org.junit.After

@RunWith(AndroidJUnit4::class)
class RegisterInstrumentedTest {

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
            .performTextInput("shital@gmail.com")

        // Enter Password
        composeRule.onNodeWithTag("password")
            .performTextInput("shital123")

        // Click Login Button
        composeRule.onNodeWithTag("login")
            .performClick()

        // Wait for navigation (max 10 seconds)
        composeRule.waitUntil(timeoutMillis = 10000) {
            try {
                intended(hasComponent(DashboardActivity::class.java.name))
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }
}