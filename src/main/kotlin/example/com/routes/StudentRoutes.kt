package example.com.routes

import example.com.services.StudentService
import example.com.services.StudentServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.studentRoutes() {
    val studentService by inject<StudentService>()
    route("/students") {
        get{
            val students = studentService.getAllStudents()
            call.respond(HttpStatusCode.OK, students)
        }
    }
}