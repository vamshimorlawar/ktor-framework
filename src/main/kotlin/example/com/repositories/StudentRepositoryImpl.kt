package example.com.repositories

import example.com.models.Student
import example.com.models.StudentType
import example.com.plugins.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class StudentRepositoryImpl : StudentRepository {
    private fun toStudentType(row: ResultRow): StudentType = StudentType(
        id = row[Student.id],
        name = row[Student.name],
        school = row[Student.school]
    )

    override suspend fun getAllStudents(): List<StudentType> = dbQuery {
        Student.selectAll().map { toStudentType(it) }
    }

    override suspend fun getStudentById(id: Int): StudentType? = dbQuery {
        Student.selectAll().where { Student.id eq id }.map { toStudentType(it) }.singleOrNull()
    }

    override suspend fun addStudent(newStudent: StudentType): StudentType? = dbQuery {
        val insertStmt = Student.insert {
            it[name] = newStudent.name
            it[school] = newStudent.school
        }
        insertStmt.resultedValues?.singleOrNull()?.let { toStudentType(it) }
    }

    override suspend fun updateStudent(id: Int, updatedStudent: StudentType): Boolean = dbQuery {
        Student.update({ Student.id eq id }) {
            it[name] = updatedStudent.name
            it[school] = updatedStudent.school
        } > 0
    }

    override suspend fun deleteStudentById(id: Int): Boolean = dbQuery {
        Student.deleteWhere { Student.id eq id } > 0
    }

}