package example.com.routes

import example.com.models.InputStudentType
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

        get("/name/{name}"){
            val name = call.parameters["name"]
            if(name==null){
                call.respond(HttpStatusCode.BadRequest, "name cannot be null")
                return@get
            }
            val student = studentService.getStudentByName(name)
            call.respond(HttpStatusCode.OK, Response<StudentType>("Student by name = $name", student))
        }

        get("/school/{school}"){
            val school = call.parameters["school"]
            if(school == null){
                call.respond(HttpStatusCode.OK, "school name cannot be null")
                return@get
            }
            val student = studentService.getStudentBySchool(school)
            call.respond(HttpStatusCode.OK, Response("Student by school = $school", student))
        }

        post {
//            TODO(How to receive one or more inputs here like (InputStudentType || InputStudentType[])?)
            val newStudent = call.receive<InputStudentType>()
//            TODO(How to trigger the exception with the Request Body not matching the expected format instead of auto BadRequest?)
            val student = studentService.addStudent(newStudent)
            call.respond(HttpStatusCode.OK, Response("New Student details added", student))
        }

        put("/{id}"){
            val id = call.parameters["id"]
            val updatedStudent = call.receive<InputStudentType>()

            if(id == null){
                call.respond(HttpStatusCode.BadRequest, "name cannot be null")
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

        delete("/name/{name}"){
            val name = call.parameters["name"]
            if(name==null){
                call.respond(HttpStatusCode.BadRequest, "name cannot be null")
                return@delete
            }
            studentService.deleteStudentByName(name)
            call.respond(HttpStatusCode.OK, "Deleted student with name $name")
        }

        delete("/school/{school}"){
            val school = call.parameters["school"]
            if(school == null){
                call.respond(HttpStatusCode.OK, "school name cannot be null")
                return@delete
            }
            studentService.deleteStudentBySchool(school)
            call.respond(HttpStatusCode.OK, "Deleted student with school $school")
        }
    }
}