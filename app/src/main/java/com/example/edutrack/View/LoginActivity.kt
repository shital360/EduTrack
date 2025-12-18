package com.example.edutrack.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutrack.R
import com.example.edutrack.repository.UserRepoImpl
import com.example.edutrack.viewmodel.UserViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginBody()
        }
    }
}

@Composable
fun LoginBody() {

    val context = LocalContext.current
    val userViewModel = remember { UserViewModel(UserRepoImpl()) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }

    val PrimaryBlue = Color(0xFF64B5F6)
    val AccentBlue = Color(0xFF42A5F5)
    val Cream = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "EDUTRACK",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.edutrack),
            contentDescription = null,
            modifier = Modifier.height(180.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            singleLine = true,
            visualTransformation =
                if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { visibility = !visibility }) {
                    Icon(
                        painter = painterResource(
                            if (visibility)
                                R.drawable.baseline_visibility_off_24
                            else
                                R.drawable.baseline_visibility_24
                        ),
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Forgot Password?",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    context.startActivity(
                        Intent(context, ForgotPasswordActivity::class.java)
                    )
                },
            textAlign = TextAlign.End,
            color = AccentBlue
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                userViewModel.login(email.trim(), password.trim()) { success, msg ->
                    if (success) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        context.startActivity(
                            Intent(context, DashboardActivity::class.java)
                        )
                        (context as? ComponentActivity)?.finish()
                    } else {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text("Log In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Don’t have an account? Create one",
            color = AccentBlue,
            modifier = Modifier.clickable {
                context.startActivity(
                    Intent(context, RegistrationActivity::class.java)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginBody()
}
