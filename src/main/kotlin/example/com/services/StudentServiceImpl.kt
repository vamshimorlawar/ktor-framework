package example.com.services

import example.com.models.Student
import example.com.models.StudentType
import example.com.plugins.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class StudentServiceImpl: StudentService {
    private fun toStudentType(row: ResultRow): StudentType = StudentType(
        id = row[Student.id],
        name = row[Student.name],
        school = row[Student.school]
    )

    override suspend fun getAllStudents(): List<StudentType> = dbQuery {
        Student.selectAll().map { toStudentType(it) }
    }
}