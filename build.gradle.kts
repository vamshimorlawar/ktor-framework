val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val exposed_version: String by project
val h2_version: String by project
val postgres_version: String by project
val hikaricp_version: String by project
val flyway_core_version: String by project
val hoplite_version: String by project
val mockk_version: String by project
val kotlin_test_version: String by project
val kotlinx_coroutines_test_version: String by project
val ktor_server_test_version: String by project
val kotest_version: String by project

plugins {
    kotlin("jvm") version "2.0.10"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.10"
    id("org.flywaydb.flyway") version "8.5.4"
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")

    //Kotest
    implementation("io.kotest.extensions:kotest-extensions-koin:$kotest_version")

    // database pooling
    implementation("com.zaxxer:HikariCP:$hikaricp_version")

    // database migration
    implementation("org.flywaydb:flyway-core:$flyway_core_version")

    // hoplite - configuration management system
    implementation("com.sksamuel.hoplite:hoplite-yaml:$hoplite_version")

    // ktor server test host
    testImplementation("io.ktor:ktor-server-test-host-jvm")

    testImplementation("io.ktor:ktor-server-tests:$ktor_server_test_version")

    // mockk for tests
    testImplementation("io.mockk:mockk:$mockk_version")

    // test functionalities
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_test_version")

    // testing asynchronous
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinx_coroutines_test_version")
}

// TODO(How to use hoplite config here?)
flyway {
    url = "jdbc:postgresql://localhost:5432/ktor"
    user = "postgres"
    password = "password"
    baselineOnMigrate = true
}