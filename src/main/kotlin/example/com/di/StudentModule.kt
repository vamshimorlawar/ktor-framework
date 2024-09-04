package example.com.di

import example.com.repositories.StudentRepository
import example.com.repositories.StudentRepositoryImpl
import example.com.services.StudentService
import example.com.services.StudentServiceImpl
import org.koin.dsl.module

val studentModule = module {
    single<StudentRepository> { StudentRepositoryImpl() }
    single<StudentService> { StudentServiceImpl(get()) }
}