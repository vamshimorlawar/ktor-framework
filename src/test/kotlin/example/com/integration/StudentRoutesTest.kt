package example.com.integration

import io.mockk.*
import io.ktor.http.*
import kotlin.test.Test
import io.ktor.server.testing.*
import io.ktor.client.request.*
import kotlin.test.assertEquals
import example.com.models.StudentType
import example.com.repositories.StudentRepository
import example.com.services.StudentServiceImpl

class StudentRoutesTest {
    // Mock the repository
    private val mockStudentRepository = mockk<StudentRepository>()
    private val mockStudentService = StudentServiceImpl(mockStudentRepository)


//    @Test
//    fun testGetStudents() = testApplication {
//        // Define what the mock should return
//        val students = listOf(
//            StudentType(id = 1, name = "John Doe", school = "IITK")
//        )
//        coEvery { mockStudentRepository.getAllStudents() } returns students
//
//        // Make a request to the /students endpoint
//        val response = client.get("/students")
//
//        // Assert the response
//        assertEquals(HttpStatusCode.OK, response.status)
////        assertEquals(
////            """{"message":"All students data","data":[{"id":1,"name":"John Doe"}]}""",
////            response.bodyAsText()
////        )
//
//        // Verify the interaction with the mock
//        coVerify { mockStudentRepository.getAllStudents() }
//    }
}