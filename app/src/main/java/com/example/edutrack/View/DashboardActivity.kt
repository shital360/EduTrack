package com.example.edutrack.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutrack.R

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardScreen()
        }
    }
}
@Composable
fun DashboardScreen() {

    // 🎨 COLORS (Interior Theme)
    val BackgroundBeige = Color(0xFFF7EFE6)
    val PrimaryBrown = Color(0xFF8D6E63)
    val TextDark = Color(0xFF333333)
    val CardWhite = Color.White

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundBeige)
                .padding(16.dp)
        ) {

            // 🔝 HEADER
            Text(
                text = "    EDUTRACK",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBrown
            )
            Text(
                text = "Manage students. Track progress.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🛋️ FEATURED CARD
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(R.drawable.teacher), // add image
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "Student Portal",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // 📂 CATEGORIES
            Text(
                text =  "Sections",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDark
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow {
                items(4) { index ->
                    CategoryCard(
                        title = when (index) {
                            0 -> "Student"
                            1 -> "Teachers"
                            2 -> "Courses"
                            else -> "Attendance"
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))


            Text(
                text = "Recent Activities",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDark
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(4) {
                    DesignCard()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 📅 CTA BUTTON
            Button(
                onClick = { /* Book consultation */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown)
            ) {
                Text(
                    text = "View Student Records",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CategoryCard(title: String) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(width = 140.dp, height = 80.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(title, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun DesignCard() {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.coursesbanner), // add image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(
                text = "Student Record"
                ,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val PrimaryBrown = Color(0xFF8D6E63)

    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(painterResource(R.drawable.student), contentDescription = null)
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(painterResource(R.drawable.teacher), contentDescription = null)
            },
            label = { Text("Designs") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(painterResource(R.drawable.courses), contentDescription = null)
            },
            label = { Text("Saved") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(painterResource(R.drawable.attendance), contentDescription = null)
            },
            label = { Text("Profile") }
        )
    }
}
