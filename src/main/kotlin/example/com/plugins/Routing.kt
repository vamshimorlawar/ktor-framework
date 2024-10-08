package example.com.plugins

import example.com.routes.studentRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Student App with KTOR, KOIN, Postgres, Flyway using Service-Repository Pattern!")
        }
        studentRoutes()
    }
}
