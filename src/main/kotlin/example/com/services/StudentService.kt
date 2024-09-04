package example.com.services

import example.com.models.StudentType

interface StudentService {
    suspend fun getAllStudents(): List<StudentType>
    suspend fun getStudentById(id: Int?): StudentType

    suspend fun addStudent(newStudent: StudentType): StudentType

    suspend fun updateStudent(id: Int?, updatedStudent: StudentType): Boolean

    suspend fun deleteStudentById(id: Int?): Boolean
}