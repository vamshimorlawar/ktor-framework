package example.com.plugins

import io.ktor.server.application.*
import io.opentelemetry.instrumentation.ktor.v2_0.server.KtorServerTracing
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk

fun Application.configureKtorServerTracing() {
    val otel = AutoConfiguredOpenTelemetrySdk.initialize().openTelemetrySdk
    install(KtorServerTracing) {
        setOpenTelemetry(otel)
    }
}