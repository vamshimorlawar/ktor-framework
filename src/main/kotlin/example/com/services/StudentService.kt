package example.com.services

import example.com.models.StudentType

interface StudentService {
    suspend fun getAllStudents(): List<StudentType>
}