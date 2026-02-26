package com.example.edutrack.repository

import com.example.edutrack.model.StudentModel

class StudentRepoImpl : StudentRepo {

    private val studentList = mutableListOf(
        StudentModel("077BCT001","Shital Dangol","B.E Computer"),
        StudentModel("077BCT002","Ram Thapa","B.E Computer"),
        StudentModel("077BCT003","Gita Shrestha","B.E Civil"),
        StudentModel("077BCT004","Anita Gurung","B.E Computer"),
        StudentModel("077BCT005","Shyam Tamang","B.E Electrical")
    )

    override fun getStudents(): List<StudentModel> {
        return studentList
    }

    override fun addStudent(student: StudentModel) {
        studentList.add(student)
    }

    override fun deleteStudent(student: StudentModel) {
        studentList.remove(student)
    }
}