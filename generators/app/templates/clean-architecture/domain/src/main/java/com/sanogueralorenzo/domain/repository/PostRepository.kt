package <%= appPackage %>.domain.repository

import <%= appPackage %>.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    val key: String

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}
