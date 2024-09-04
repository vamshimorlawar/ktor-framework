package example.com.unit

import example.com.models.StudentType
import example.com.repositories.StudentRepository
import example.com.services.StudentServiceImpl
import io.ktor.server.plugins.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class StudentServiceImplTest {
    private val repository: StudentRepository = mockk()
    private val service = StudentServiceImpl(repository)

    @Test
    fun `getAllStudents should return list of students`() = runTest {
        // Given
        val students = listOf(
            StudentType(id = 1, name = "Vamshi", school = "IITK"),
            StudentType(id = 2, name = "Ebaad", school = "IITM")
        )
        coEvery { repository.getAllStudents() } returns students

        // When
        val result = service.getAllStudents()

        // Then
        assertEquals(students, result)
        coVerify { repository.getAllStudents() }
    }

    @Test
    fun `getStudentById should return student if found`() = runTest {
        // Given
        val student = StudentType(id = 1, name = "John Doe", school = "IITK")
        coEvery { repository.getStudentById(1) } returns student

        // When
        val result = service.getStudentById(1)

        // Then
        assertEquals(student, result)
        coVerify { repository.getStudentById(1) }
    }

    @Test
    fun `getStudentById should throw BadRequestException if id is null`() = runTest {
        // When & Then
        assertFailsWith<BadRequestException> {
            service.getStudentById(null)
        }
    }

    @Test
    fun `getStudentById should throw NotFoundException if student not found`() = runTest {
        // Given
        coEvery { repository.getStudentById(999) } returns null

        // When & Then
        assertFailsWith<NotFoundException> {
            service.getStudentById(999)
        }
    }

    @Test
    fun `addStudent should return student details if student added successfully`() = runTest {
        // Given
        val newStudent = StudentType(name = "Vamshi", school = "IITK")
        coEvery { repository.addStudent(newStudent) } returns newStudent

        // When
        val result = service.addStudent(newStudent)

        // Then
        assertEquals(newStudent, result)
        coVerify { repository.addStudent(newStudent) }
    }

    @Test
    fun `addStudent should throw exception if student addition failed`() = runTest {
        // Given
        val newStudent = StudentType(name = "Vamshi", school = "IITK")
        coEvery { repository.addStudent(newStudent) } returns null

        // When&Then
        val exception = assertFailsWith<Exception> {
            service.addStudent(newStudent)
        }

        assertEquals(exception.message, "ADD New Student failed, returned null from the repository")
    }

    @Test
    fun `updateStudent should return true boolean if the student exists and data updated successfully`() = runTest {
        // Given
        val id = 1
        val updatedStudent = StudentType(name = "Vamshi", school = "IITK")
        val student = StudentType(id = 1, name = "Vamshi", school = "IITK")
        coEvery { repository.getStudentById(id) } returns student
        coEvery { repository.updateStudent(id, updatedStudent) } returns true

        // When
        val result = service.updateStudent(id, updatedStudent)

        // Then
        assertTrue(result)
    }

    @Test
    fun `updateStudent should fail with BadRequestException if id is null`() = runTest {
        // Given
        val id = null
        val updatedStudent = StudentType(name = "Vamshi", school = "IITK")

        // When&Then
        val exception = assertFailsWith<BadRequestException> {
            service.updateStudent(id, updatedStudent)
        }

        assertEquals(exception.message, "Invalid ID for a student received")
    }

    @Test
    fun `updateStudent should fail with Exception if student exists but update is failed`() = runTest {
        // Given
        val id = 1
        val updatedStudent = StudentType(name = "Vamshi", school = "IITK")
        val student = StudentType(id = 1, name = "Vamshi", school = "IITK")
        coEvery { repository.getStudentById(id) } returns student
        coEvery { repository.updateStudent(id, updatedStudent) } returns false

        //When&Then
        val exception = assertFailsWith<Exception> { service.updateStudent(id, updatedStudent) }

        assertEquals(exception.message, "UPDATE Student failed, returned false from the repository")
    }

    @Test
    fun `updateStudent should fail with Exception if student doesn't exists`() = runTest {
        // Given
        val id = 999
        val updatedStudent = StudentType(name = "Vamshi", school = "IITK")

        coEvery { repository.getStudentById(id) } returns null

        //When&Then
        val exception = assertFailsWith<NotFoundException> { service.updateStudent(id, updatedStudent) }

        assertEquals(exception.message, "Student with id $id doesn't exist")
    }

    @Test
    fun `deleteStudent should return true boolean if the student exists and data deleted successfully`() = runTest {
        // Given
        val id = 1
        val student = StudentType(id = 1, name = "Vamshi", school = "IITK")
        coEvery { repository.getStudentById(id) } returns student
        coEvery { repository.deleteStudentById(id) } returns true

        // When
        val result = service.deleteStudentById(id)

        // Then
        assertTrue(result)
    }

    @Test
    fun `deleteStudent should fail with BadRequestException if id is null`() = runTest {
        // Given
        val id = null

        // When&Then
        val exception = assertFailsWith<BadRequestException> {
            service.deleteStudentById(id)
        }

        assertEquals(exception.message, "Invalid ID for a student received")
    }

    @Test
    fun `deleteStudent should fail with Exception if student exists but delete is failed`() = runTest {
        // Given
        val id = 1
        val student = StudentType(id = 1, name = "Vamshi", school = "IITK")
        coEvery { repository.getStudentById(id) } returns student
        coEvery { repository.deleteStudentById(id) } returns false

        //When&Then
        val exception = assertFailsWith<Exception> { service.deleteStudentById(id) }

        assertEquals(exception.message, "DELETE Student failed, returned false from the repository")
    }

    @Test
    fun `deleteStudent should fail with Exception if student doesn't exists`() = runTest {
        // Given
        val id = 999
        coEvery { repository.getStudentById(id) } returns null

        //When&Then
        val exception = assertFailsWith<NotFoundException> { service.deleteStudentById(id) }

        assertEquals(exception.message, "Student with id $id doesn't exist")
    }
}