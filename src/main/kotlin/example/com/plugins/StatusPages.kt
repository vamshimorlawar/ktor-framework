package example.com.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
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
}