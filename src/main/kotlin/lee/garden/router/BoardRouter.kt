package lee.garden.router

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.boardRouter() {
    routing {
        route("/board") {
            get{
                call.respond("this is board api")
            }
            get("/{id}") {
                call.respond("this is board detail api, id = " + call.parameters["id"])
            }
        }
    }
}