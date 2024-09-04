package example.com.repositories

import example.com.models.StudentType

interface StudentRepository {
    //    READ
    suspend fun getAllStudents(): List<StudentType>
    suspend fun getStudentById(id: Int): StudentType?

    //    CREATE
    suspend fun addStudent(newStudent: StudentType): StudentType?

    //    UPDATE
    suspend fun updateStudent(id: Int, updatedStudent: StudentType): Boolean

    //    DELETE
    suspend fun deleteStudentById(id: Int): Boolean
}