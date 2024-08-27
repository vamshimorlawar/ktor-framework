package example.com.plugins

import example.com.routes.studentRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when(cause) {
                is NotFoundException -> call.respondText(
                    text = "404: ${cause.message}",
                    status = HttpStatusCode.NotFound
                )
                is BadRequestException -> call.respondText(
                    text = "400: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )
            }
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondText("Student App with KTOR, KOIN, Postgres, Flyway using Service-Repository Pattern!")
        }
        studentRoutes()
    }
}
