package lee.garden.router

import io.ktor.application.*
import io.ktor.routing.*
import lee.garden.service.PostService

fun Application.baseRouter() {
    routing {
        postRouter(PostService())
    }
}