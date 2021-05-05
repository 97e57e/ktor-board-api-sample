package lee.garden.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lee.garden.dto.post.PostResponse
import lee.garden.entity.Post
import lee.garden.entity.Posts
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction

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
}