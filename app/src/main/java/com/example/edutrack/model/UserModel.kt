package com.example.edutrack.model

data class UserModel(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val dob: String = "",
    val email: String = "",
    val role: String = ""   // VERY IMPORTANT
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "firstName" to firstName,
            "lastName" to lastName,
            "gender" to gender,
            "dob" to dob,
            "email" to email,
            "role" to role
        )
    }
}