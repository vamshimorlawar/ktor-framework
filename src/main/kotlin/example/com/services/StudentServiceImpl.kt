package example.com.services

import example.com.models.StudentType
import example.com.repositories.StudentRepository
import io.ktor.server.plugins.*

class StudentServiceImpl(private val repository: StudentRepository) : StudentService {
    override suspend fun getAllStudents(): List<StudentType> {
        return repository.getAllStudents()
    }

    override suspend fun getStudentById(id: Int?): StudentType {
        if (id == null) {
            throw BadRequestException("Invalid ID for a student received")
        }
        val student = repository.getStudentById(id)
        if (student == null) {
            throw NotFoundException("Student with ID $id doesn't exist")
        } else {
            return student
        }
    }


    override suspend fun addStudent(newStudent: StudentType): StudentType {
        val student = repository.addStudent(newStudent)
        if (student == null) {
            throw Exception("ADD New Student failed, returned null from the repository")
        } else {
            return student
        }
    }

    override suspend fun updateStudent(id: Int?, updatedStudent: StudentType): Boolean {
        if (id == null) {
            throw BadRequestException("Invalid ID for a student received")
        }
        val studentExists = repository.getStudentById(id)
        if (studentExists != null) {
            val response = repository.updateStudent(id, updatedStudent)
            if (!response) {
                throw Exception("UPDATE Student failed, returned false from the repository")
            } else {
                return true
            }
        } else {
            throw NotFoundException("Student with id $id doesn't exist")
        }
    }

    override suspend fun deleteStudentById(id: Int?): Boolean {
        if (id == null) {
            throw BadRequestException("Invalid ID for a student received")
        }
        val student = repository.getStudentById(id)
        if (student != null) {
            val response = repository.deleteStudentById(id)
            if (!response) {
                throw Exception("DELETE Student failed, returned false from the repository")
            } else {
                return true
            }
        } else {
            throw NotFoundException("Student with id $id doesn't exist")
        }

    }
}