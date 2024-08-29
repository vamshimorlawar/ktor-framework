package example.com.configurations

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource

data class Database(
    val url: String,
    val user: String,
    val password: String
)

data class Hikari(
    val driverClassName: String,
    val jdbcUrl: String,
    val username: String,
    val password: String,
    val maximumPoolSize: Int,
    val isAutoCommit: Boolean,
    val transactionIsolation: String,
)

data class Flyway(
    val url: String,
    val user: String,
    val password: String,
    val baselineOnMigrate: Boolean
)


data class Config(
    val database: Database,
    val hikari: Hikari,
    val flyway: Flyway
)

val config = ConfigLoaderBuilder.default()
    .addResourceSource("/application-dev.yaml")
    .build().loadConfigOrThrow<Config>()

