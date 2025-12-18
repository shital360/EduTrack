package com.example.edutrack.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutrack.R
import com.example.edutrack.View.ui.theme.EduTrackTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        EduTrackSplash()
        }
    }
}
@Composable
fun EduTrackSplash() {

    val context = LocalContext.current
    val activity = context as Activity

    // 💙 EduTrack Light Blue & Cream Palette
    val PrimaryBlue = Color(0xFF64B5F6)
    val SecondaryBlue = Color(0xFF90CAF9)
    val AccentBlue = Color(0xFF42A5F5)
    val Cream = Color(0xFFFFF8E1)

    // ⏳ Splash delay + navigation
    LaunchedEffect(Unit) {
        delay(2500)
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        activity.finish()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(PrimaryBlue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 🖼 App Logo
            Image(
                painter = painterResource(R.drawable.edutrack),
                contentDescription = "EduTrack Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "EDUTRACK",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Track your learning journey",
                fontSize = 13.sp,
                color = Cream.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(50.dp))

            CircularProgressIndicator(
                color = Cream,
                strokeWidth = 3.dp
            )
        }
    }
}


