package example.com.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Student: Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val school = varchar("school", 255)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class StudentType(
    val id: Int,
    val name: String,
    val school: String
)