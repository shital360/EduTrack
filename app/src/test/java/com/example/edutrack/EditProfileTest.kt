package com.example.edutrack

import com.example.edutrack.repository.UserRepo
import com.example.edutrack.viewmodel.UserViewModel
import  com.example.edutrack.model.UserModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
class EditProfileTest {
    @Test
    fun edit_profile_success_test() {
        val repo = mock<UserRepo>()
        val viewModel = UserViewModel(repo)

        val user = UserModel(
            id = "123",
            firstName = "Shital",
            lastName = "",
            gender = "",
            dob = "",
            email = "Shital@gmail.com",
            role = "student"
        )

        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, String) -> Unit>(2)
            callback(true, "Profile updated success")
            null
        }.`when`(repo).editProfile(eq("123"), eq(user), any())

        var successResult = false
        var messageResult = ""

        viewModel.editProfile("123", user) { success, msg ->
            successResult = success
            messageResult = msg
        }

        assertTrue(successResult)
        assertEquals("Profile updated success", messageResult)

        verify(repo).editProfile(eq("123"), eq(user), any())
    }
}