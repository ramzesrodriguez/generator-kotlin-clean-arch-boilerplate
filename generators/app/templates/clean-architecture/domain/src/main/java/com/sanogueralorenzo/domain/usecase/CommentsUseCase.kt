package <%= appPackage %>.domain.usecase

import <%= appPackage %>.domain.model.Comment
import <%= appPackage %>.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

class CommentsUseCase @Inject constructor(private val repository: CommentRepository) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> = repository.get(postId, refresh)
}
