package com.example.edutrack.repository

import com.example.edutrack.model.CourseModel

interface CourseRepo {

    fun getCourses(): List<CourseModel>

}