package com.example.edutrack.viewmodel

import androidx.lifecycle.ViewModel
import com.example.edutrack.model.CourseModel
import com.example.edutrack.repository.CourseRepo
import com.example.edutrack.repository.CourseRepoImpl

class CourseViewModel : ViewModel() {

    private val repo = CourseRepoImpl()

    fun getCourses(): List<CourseModel> {
        return repo.getCourses()
    }
}