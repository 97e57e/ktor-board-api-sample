package lee.garden.router

import io.ktor.application.*
import io.ktor.routing.*

fun Application.baseRouter() {
    routing {
        boardRouter()
    }
}