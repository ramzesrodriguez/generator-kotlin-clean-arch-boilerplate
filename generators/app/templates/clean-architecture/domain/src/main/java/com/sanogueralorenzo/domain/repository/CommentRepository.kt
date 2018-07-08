package <%= appPackage %>.domain.repository

import <%= appPackage %>.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    val key: String

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}
