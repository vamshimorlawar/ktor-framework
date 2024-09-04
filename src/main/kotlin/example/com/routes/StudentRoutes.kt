package example.com.routes

import example.com.models.Response
import example.com.models.StudentType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import example.com.services.StudentService
import io.ktor.server.request.*

fun Routing.studentRoutes() {
    val studentService by inject<StudentService>()
    route("/students") {
        get {
            val students = studentService.getAllStudents()
            call.respond(HttpStatusCode.OK, Response("All students data", students))
        }

        get("/id/{id}") {
            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "id cannot be null")
                return@get
            }
            val student = studentService.getStudentById(id.toIntOrNull())
            call.respond(HttpStatusCode.OK, Response("Student by id = $id", student))
        }

        post {
            val newStudent = call.receive<StudentType>()
            if (newStudent.name == null) {
                call.respond(HttpStatusCode.BadRequest, "Student name is required in request body")
                return@post
            }
            if (newStudent.school == null) {
                call.respond(HttpStatusCode.BadRequest, "Student school is required in request body")
                return@post
            }

            val student = studentService.addStudent(newStudent)
            call.respond(HttpStatusCode.OK, Response("New Student details added", student))
        }

        put("/{id}") {
            val id = call.parameters["id"]
            val updatedStudent = call.receive<StudentType>()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "name cannot be null")
                return@put
            }
            if (updatedStudent.name == null) {
                call.respond(HttpStatusCode.BadRequest, "Student name is required in request body")
                return@put
            }
            if (updatedStudent.school == null) {
                call.respond(HttpStatusCode.BadRequest, "Student school is required in request body")
                return@put
            }
            studentService.updateStudent(id.toIntOrNull(), updatedStudent)
            call.respond(HttpStatusCode.OK, "Student details updated")
        }

        delete("/id/{id}") {
            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "id cannot be null")
                return@delete
            }
            studentService.deleteStudentById(id.toIntOrNull())
            call.respond(HttpStatusCode.OK, "Deleted student with id $id")
        }
    }
}