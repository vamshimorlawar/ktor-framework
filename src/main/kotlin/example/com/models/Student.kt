package example.com.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Student : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val school = varchar("school", 255)

    override val primaryKey = PrimaryKey(id)
}

//  TODO(Is it good practice to create multiple data classes to define the different return data types?) -> JSON Ignore
@Serializable
data class StudentType(
    val id: Int? = null,
    val name: String,
    val school: String
)