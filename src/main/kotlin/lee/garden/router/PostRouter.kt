package lee.garden.router

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lee.garden.dto.post.PostSaveRequest
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
            post {
                val body = call.receive<PostSaveRequest>()
                postService.createPost(body)
                call.response.status(HttpStatusCode.Created)
            }
        }
    }
}