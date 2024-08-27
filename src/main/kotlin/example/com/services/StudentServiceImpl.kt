package example.com.services

import example.com.models.InputStudentType
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

    override suspend fun getStudentByName(name: String): StudentType {
        val student = repository.getStudentByName(name)
        if (student == null) {
            throw NotFoundException("Student with Name $name doesn't exist")
        } else {
            return student
        }
    }

    override suspend fun getStudentBySchool(school: String): StudentType {
        val student = repository.getStudentBySchool(school)
        if (student == null) {
            throw NotFoundException("Student with School $school doesn't exist")
        } else {
            return student
        }
    }

    override suspend fun addStudent(newStudent: InputStudentType): StudentType {
        val student = repository.addStudent(newStudent)
        if(student == null){
//            TODO(What exception should be thrown here? Internal Server Error)
            throw Exception("ADD New Student failed, returned null from the repository")
        }else{
            return student
        }
    }

    override suspend fun updateStudent(id: Int?, updatedStudent: InputStudentType): Boolean {
        if(id == null){
            throw BadRequestException("Invalid ID for a student received")
        }
        val response = repository.updateStudent(id, updatedStudent)
//        TODO(How to know UPDATE failed because of the ID not found or some other reason?)
        if(!response){
            throw Exception("UPDATE Student failed, returned false from the repository")
        }else{
            return true
        }
    }

    override suspend fun deleteStudentById(id: Int?): Boolean {
        if(id == null){
            throw BadRequestException("Invalid ID for a student received")
        }
        val response = repository.deleteStudentById(id)
//        TODO(How to know UPDATE failed because of the ID not found or some other reason?)
        if(!response){
            throw Exception("DELETE Student failed, returned false from the repository")
        }else{
            return true
        }
    }

    override suspend fun deleteStudentByName(name: String): Boolean {
        val response = repository.deleteStudentByName(name)
        if(!response){
            throw Exception("DELETE Student failed, returned false from the repository")
        }else{
            return true
        }
    }

    override suspend fun deleteStudentBySchool(school: String): Boolean {
        val response = repository.deleteStudentBySchool(school)
        if(!response){
            throw Exception("DELETE Student failed, returned false from the repository")
        }else{
            return true
        }
    }
}