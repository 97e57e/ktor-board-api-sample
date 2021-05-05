package lee.garden.router

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import lee.garden.service.PostService

fun Application.postRouter(postService: PostService) {
    routing {
        route("/post") {
            get{
                call.respond(postService.getAll())
            }
            get("/{id}") {
                call.respond("this is post detail api, id = " + call.parameters["id"])
            }
        }
    }
}