package lee.garden.dto.post

import lee.garden.entity.Post
import java.time.LocalDateTime

data class PostResponse(val id: Int,
                        val content: String,
                        val createdAt: LocalDateTime,
                        val updatedAt: LocalDateTime) {
    companion object {
        fun of(post: Post) =
            PostResponse(post.id.value, post.content, post.createdAt, post.updatedAt)
    }
}
