package example.com.plugins

import example.com.repositories.StudentRepository
import example.com.repositories.StudentRepositoryImpl
import example.com.services.StudentService
import example.com.services.StudentServiceImpl
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(studentModule)
    }
}

val studentModule = module {
    single<StudentRepository> {StudentRepositoryImpl()}
    single<StudentService> { StudentServiceImpl(get()) }
}
