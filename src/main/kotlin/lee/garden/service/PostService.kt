package lee.garden.service

import io.ktor.features.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lee.garden.dto.post.PostEditRequest
import lee.garden.dto.post.PostResponse
import lee.garden.dto.post.PostSaveRequest
import lee.garden.entity.Post
import lee.garden.entity.Posts
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class PostService {
    /**
     * exposed 에서 db에 접근 하려면 transaction block 안에 있어야 한다.
     * */
    suspend fun getAll() = withContext(Dispatchers.IO) {
        transaction {
            Post.all()
                .orderBy(Posts.id to SortOrder.DESC)
                .map(PostResponse.Companion::of)
                .toList()
        }
    }

    suspend fun getById(id: Int) = withContext(Dispatchers.IO) {
        transaction {
            val post = Post.findById(id) ?: throw NotFoundException()
            PostResponse.of(post)
        }
    }

    suspend fun createPost(postSaveRequest: PostSaveRequest) = withContext(Dispatchers.IO) {
        transaction {
            Post.new { this.content = postSaveRequest.content }
        }
    }

    suspend fun editPost(id: Int, postEditRequest: PostEditRequest) = withContext(Dispatchers.IO) {
        transaction {
            val post = Post.findById(id) ?: throw NotFoundException()
            post.apply {
                content = postEditRequest.content
                updatedAt = LocalDateTime.now()
            }
        }
    }

}