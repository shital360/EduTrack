package com.example.edutrack.repository

import com.example.edutrack.model.CourseModel

class CourseRepoImpl : CourseRepo {

    private val courseList = listOf(
        CourseModel("CS101","Computer Fundamentals"),
        CourseModel("CS102","Data Structures"),
        CourseModel("CS103","Database Systems"),
        CourseModel("CS104","Operating Systems"),
        CourseModel("CS105","Computer Networks")
    )

    override fun getCourses(): List<CourseModel> {
        return courseList
    }
}