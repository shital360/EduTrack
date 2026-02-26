package com.example.edutrack.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutrack.R
import com.example.edutrack.model.UserModel
import com.example.edutrack.repository.UserRepoImpl
import com.example.edutrack.viewmodel.UserViewModel


class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationBody()
        }
    }
}

@Composable
fun RegistrationBody() {

    val context = LocalContext.current
    val userViewModel = remember { UserViewModel(UserRepoImpl()) }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // 🎓 EduTrack Colors
    val PrimaryBlue = Color(0xFF64B5F6)
    val SecondaryBlue = Color(0xFF90CAF9)
    val AccentBlue = Color(0xFF42A5F5)
    val Cream = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "EDUTRACK",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AccentBlue
        )

        Text(
            text = "Track students. Manage success.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.edutrack),
            contentDescription = null,
            modifier = Modifier.height(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        EduInput("Full Name", fullName) { fullName = it }
        Spacer(modifier = Modifier.height(12.dp))

        EduInput("Email Address", email, KeyboardType.Email) { email = it }
        Spacer(modifier = Modifier.height(12.dp))

        EduPassword("Password", password, passwordVisible, { passwordVisible = !passwordVisible }) {
            password = it
        }
        Spacer(modifier = Modifier.height(12.dp))

        EduPassword(
            "Confirm Password",
            confirmPassword,
            confirmPasswordVisible,
            { confirmPasswordVisible = !confirmPasswordVisible }
        ) {
            confirmPassword = it
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                    Toast.makeText(context, "All fields required", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (password != confirmPassword) {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                userViewModel.register(email.trim(), password.trim()) { success, message, userId ->
                    if (success) {
                        val user = UserModel(userId, fullName, email)
                        userViewModel.addUserToDatabase(userId, user) { _, _ ->
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text("Create Account", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Already registered? Login",
            color = AccentBlue,
            modifier = Modifier.clickable {
                context.startActivity(Intent(context, LoginActivity::class.java))
            }
        )
    }
}

/* ---------- Reusable Inputs ---------- */

@Composable
fun EduInput(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EduPassword(
    label: String,
    value: String,
    visible: Boolean,
    onToggle: () -> Unit,

    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onToggle) {
                Icon(
                    painter = painterResource(
                        if (visible) R.drawable.baseline_visibility_off_24
                        else R.drawable.baseline_visibility_24
                    ),
                    contentDescription = null
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegistrationBody()
}
