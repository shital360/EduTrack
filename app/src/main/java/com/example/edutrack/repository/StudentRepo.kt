package com.example.edutrack.repository

import com.example.edutrack.model.StudentModel

interface StudentRepo {

    fun getStudents(): List<StudentModel>

    fun addStudent(student: StudentModel)

    fun deleteStudent(student: StudentModel)
}