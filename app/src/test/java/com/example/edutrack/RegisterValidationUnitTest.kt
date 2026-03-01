package com.example.edutrack

import com.example.edutrack.repository.UserRepo
import com.example.edutrack.viewmodel.UserViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RegisterValidationUnitTest {

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        // Dummy repo (won't be used for validation)
        val dummyRepo = object : UserRepo {
            override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {}
            override fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit) {}
            override fun addUserToDatabase(userId: String, model: com.example.edutrack.model.UserModel, callback: (Boolean, String) -> Unit) {}
            override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {}
            override fun deleteAccount(userId: String, callback: (Boolean, String) -> Unit) {}
            override fun editProfile(userId: String, model: com.example.edutrack.model.UserModel, callback: (Boolean, String) -> Unit) {}
            override fun getUserById(userId: String, callback: (Boolean, String, com.example.edutrack.model.UserModel?) -> Unit) {}
            override fun getAllUser(callback: (Boolean, String, List<com.example.edutrack.model.UserModel>?) -> Unit) {}
        }

        viewModel = UserViewModel(dummyRepo)
    }

    @Test
    fun emptyFields_returnsError() {
        val result = viewModel.validateRegistration("", "", "", "")
        assertEquals("All fields required", result)
    }

    @Test
    fun passwordMismatch_returnsError() {
        val result = viewModel.validateRegistration(
            "Shital",
            "test@gmail.com",
            "123456",
            "123"
        )
        assertEquals("Passwords do not match", result)
    }

    @Test
    fun shortPassword_returnsError() {
        val result = viewModel.validateRegistration(
            "Shital",
            "test@gmail.com",
            "123",
            "123"
        )
        assertEquals("Password must be at least 6 characters", result)
    }

    @Test
    fun validInput_returnsValid() {
        val result = viewModel.validateRegistration(
            "Shital",
            "test@gmail.com",
            "123456",
            "123456"
        )
        assertEquals("Valid", result)
    }
}