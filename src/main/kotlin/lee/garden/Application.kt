package lee.garden

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import lee.garden.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        configureMonitoring()
    }.start(wait = true)
}