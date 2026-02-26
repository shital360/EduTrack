package com.example.edutrack.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.edutrack.model.StudentModel
import com.example.edutrack.repository.StudentRepoImpl

class StudentViewModel : ViewModel() {

    private val repo = StudentRepoImpl()

    var students = mutableStateListOf<StudentModel>()
        private set

    init {
        students.addAll(repo.getStudents())
    }

    fun addStudent(student: StudentModel){
        repo.addStudent(student)
        students.add(student)
    }

    fun deleteStudent(student: StudentModel){
        repo.deleteStudent(student)
        students.remove(student)
    }
}