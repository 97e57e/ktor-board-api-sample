package lee.garden.router

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lee.garden.dto.post.PostEditRequest
import lee.garden.dto.post.PostSaveRequest
import lee.garden.service.PostService

fun Application.postRouter(postService: PostService) {
    routing {
        route("/post") {
            get{
                call.respond(postService.getAll())
            }
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()?: throw BadRequestException("Parameter Id is Null")
                call.respond(postService.getById(id))
            }
            post {
                val body = call.receive<PostSaveRequest>()
                postService.createPost(body)
                call.response.status(HttpStatusCode.Created)
            }
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()?: throw BadRequestException("Parameter Id is Null")
                val body = call.receive<PostEditRequest>()
                postService.editPost(id, body)
                call.response.status(HttpStatusCode.NoContent)
            }
        }
    }
}