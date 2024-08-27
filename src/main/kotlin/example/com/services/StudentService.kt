package example.com.services

import example.com.models.InputStudentType
import example.com.models.StudentType

interface StudentService {
    suspend fun getAllStudents(): List<StudentType>
    suspend fun getStudentById(id: Int?): StudentType?
    suspend fun getStudentByName(name: String): StudentType?
    suspend fun getStudentBySchool(school: String): StudentType?

    suspend fun addStudent(newStudent: InputStudentType): StudentType?

    suspend fun updateStudent(id: Int?, updatedStudent: InputStudentType): Boolean

    suspend fun deleteStudentById(id: Int?): Boolean
    suspend fun deleteStudentByName(name: String): Boolean
    suspend fun deleteStudentBySchool(school: String): Boolean
}