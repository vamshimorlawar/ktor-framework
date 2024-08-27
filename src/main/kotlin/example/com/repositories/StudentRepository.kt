package example.com.repositories

import example.com.models.StudentType
import example.com.models.InputStudentType

interface StudentRepository {
//    READ
    suspend fun getAllStudents(): List<StudentType>
    suspend fun getStudentById(id: Int): StudentType?
    suspend fun getStudentByName(name: String): StudentType?
    suspend fun getStudentBySchool(school: String): StudentType?

//    CREATE
    suspend fun addStudent(newStudent: InputStudentType): StudentType?

//    UPDATE
    suspend fun updateStudent(id: Int, updatedStudent: InputStudentType): Boolean

//    DELETE
    suspend fun deleteStudentById(id: Int): Boolean
    suspend fun deleteStudentByName(name: String): Boolean
    suspend fun deleteStudentBySchool(school: String): Boolean
}