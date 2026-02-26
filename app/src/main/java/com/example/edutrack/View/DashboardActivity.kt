package com.example.edutrack.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        bottomBar = {
            NavigationBar(containerColor = Color.White) {

                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Default.People, null) },
                    label = { Text("Students") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Icon(Icons.Default.MenuBook, null) },
                    label = { Text("Courses") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->

        when (selectedIndex) {

            0 -> HomeScreen(
                padding,
                onStudentsClick = { selectedIndex = 1 },
                onCoursesClick = { selectedIndex = 2 },
                onAttendanceClick = { selectedIndex = 5 },
                onResultsClick = { selectedIndex = 4}
            )

            1 -> AllStudentsScreen(padding)
            2 -> AllCoursesScreen(padding)
            3 -> ProfileScreen(padding)
            4 -> ResultsScreen(padding)
            5 -> AttendanceScreen(padding)
        }
    }
}

////////////////////////////////////////////////////
//////////////////// HOME /////////////////////////
////////////////////////////////////////////////////

@Composable
fun HomeScreen(
    padding: PaddingValues,
    onStudentsClick: () -> Unit,
    onCoursesClick: () -> Unit,
    onAttendanceClick: () -> Unit,
    onResultsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF6A5ACD), Color(0xFF8360C3))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Text("Welcome back,", color = Color.White)
                Text(
                    "Shital Dangol",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Icon(Icons.Default.Security, null,
                    tint = Color.White,
                    modifier = Modifier.size(50.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .padding(20.dp)
        ) {

            Text("Recent Activities",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                DashboardCard("👥","2","Students","Manage All",onStudentsClick,Modifier.weight(1f))
                Spacer(modifier = Modifier.width(15.dp))
                DashboardCard("📚","5","Courses","View All",onCoursesClick,Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                DashboardCard("📊","","Results","View Grades",onResultsClick,Modifier.weight(1f))
                Spacer(modifier = Modifier.width(15.dp))
                DashboardCard("📅","","Attendance","Track Records",onAttendanceClick,Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun DashboardCard(
    icon:String,
    number:String,
    title:String,
    subtitle:String,
    onClick:()->Unit,
    modifier: Modifier
){
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Column(modifier = Modifier.padding(20.dp)){
            Text(icon,fontSize = 28.sp)

            if(number.isNotEmpty()){
                Spacer(modifier = Modifier.height(10.dp))
                Text(number,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A5ACD))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(title,fontWeight = FontWeight.Bold)
            Text(subtitle,fontSize = 12.sp,color = Color.Gray)
        }
    }
}

////////////////////////////////////////////////////
//////////////////// STUDENTS /////////////////////
////////////////////////////////////////////////////

@Composable
fun AllStudentsScreen(padding: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFF4F4F4))
            .padding(20.dp)
    ){
        Text("All Students",fontSize = 22.sp,fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))

        StudentCard("Sita Rai","077BCT001")
        StudentCard("Ram Thapa","077BCT002")
        StudentCard("Gita Shrestha","077BCT003")
        StudentCard("Anita gurung","077BCT004")
        StudentCard("shyam Tamang","077BCT005")
    }
}

@Composable
fun StudentCard(name:String,id:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Column(Modifier.padding(16.dp)){
            Text(name,fontWeight = FontWeight.Bold)
            Text(id,color = Color.Gray)
        }
    }
}

////////////////////////////////////////////////////
//////////////////// COURSES //////////////////////
////////////////////////////////////////////////////

@Composable
fun AllCoursesScreen(padding: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFF4F4F4))
            .padding(20.dp)
    ){
        Text("All Courses",fontSize = 22.sp,fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        CourseCard("Science","CT601")
        CourseCard("Social","CT602")
        CourseCard("Nepali","CT603")
        CourseCard("computer","CT604")
        CourseCard("Economics","CT605")

    }
}

@Composable
fun CourseCard(title:String,code:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(title,fontWeight = FontWeight.Bold)
                Text(code,color = Color.Gray)
            }
            Text("3 Credits",color = Color(0xFF6A5ACD))
        }
    }
}

////////////////////////////////////////////////////
//////////////////// PROFILE //////////////////////
////////////////////////////////////////////////////

@Composable
fun ProfileScreen(padding: PaddingValues) {

    val context = LocalContext.current

    var isEditing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("Shital Dangol") }
    var program by remember { mutableStateOf("B.E. Computer") }
    var email by remember { mutableStateOf("shital@gmail.com") }
    var phone by remember { mutableStateOf("9841000001") }
    var roll by remember { mutableStateOf("077BCT001") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF4F4F4))
    ) {

        // 🔵 Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF6A5ACD), Color(0xFF8360C3))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(90.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    name,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    roll,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Text(
                "PERSONAL INFORMATION",
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(15.dp))

            if (isEditing) {

                OutlinedTextField(name, { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(program, { program = it }, label = { Text("Program") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(phone, { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(roll, { roll = it }, label = { Text("Roll No") }, modifier = Modifier.fillMaxWidth())

            } else {

                InfoCard("Program", program)
                InfoCard("Email", email)
                InfoCard("Phone", phone)
                InfoCard("Roll No", roll)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { isEditing = !isEditing },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Save" else "Edit Profile")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFE5E5)
                )
            ) {
                Text("Logout", color = Color.Red, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
@Composable
fun InfoCard(title: String, value: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
////////////////////////////////////////////////////
//////////////////// RESULTS //////////////////////
////////////////////////////////////////////////////

@Composable
fun ResultsScreen(padding: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFF4F4F4))
            .padding(20.dp)
    ){
        Text(
            "Results",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        ResultCard("sita Rai","077BCT001","3.8 GPA")
        ResultCard("Ram Thapa","077BCT002","3.5 GPA")
        ResultCard("Gita Shrestha","077BCT003","3.0 GPA")
        ResultCard("Anita Gurung","077BCT003","2.5 GPA")
        ResultCard("Shyam Tamang","077BCT003","2.9GPA")
    }
}

@Composable
fun ResultCard(name:String,id:String,gpa:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Column(Modifier.padding(16.dp)){
            Text(name,fontWeight = FontWeight.Bold)
            Text(id,color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                gpa,
                color = Color(0xFF6A5ACD),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
////////////////////////////////////////////////////
//////////////////// ATTENDANCE ////////////////////
////////////////////////////////////////////////////

@Composable
fun AttendanceScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFF4F4F4))
            .padding(20.dp)
    ) {

        Text(
            "Attendance",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        AttendanceCard("Database System", "CT601", 38, 45)
        AttendanceCard("AI & Machine Learn", "CT602", 40, 45)
        AttendanceCard("Software Engg.", "CT603", 30, 45)
        AttendanceCard("Computer Networks", "CT604", 42, 45)
        AttendanceCard("Operating System", "CT605", 35, 45)
    }
}


@Composable
fun ProfileItem(label:String,value:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(label,color = Color.Gray)
            Text(value,fontWeight = FontWeight.SemiBold)
        }
    }
}
@Composable
fun AttendanceCard(
    subject: String,
    code: String,
    present: Int,
    total: Int
) {

    val percentage = (present.toFloat() / total.toFloat()) * 100
    val progress = percentage / 100f

    val statusText = if (percentage >= 75) "Good" else "Warning"
    val statusColor = if (percentage >= 75)
        Color(0xFFDFF5E1) else Color(0xFFFFE0CC)

    val progressColor = when {
        percentage >= 75 -> Color(0xFF2E7D32)
        percentage >= 60 -> Color(0xFFFF9800)
        else -> Color.Red
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            // Top Row (Title + Status Badge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(subject, fontWeight = FontWeight.Bold)
                    Text(code, color = Color.Gray, fontSize = 12.sp)
                }

                Box(
                    modifier = Modifier
                        .background(statusColor, RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        statusText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (percentage >= 75)
                            Color(0xFF2E7D32) else Color(0xFFE65100)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 38/45 + Percentage Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$present/$total")
                Text(
                    "${"%.1f".format(percentage)}%",
                    fontWeight = FontWeight.Bold,
                    color = progressColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = progressColor
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDashboard(){
    DashboardScreen()
}