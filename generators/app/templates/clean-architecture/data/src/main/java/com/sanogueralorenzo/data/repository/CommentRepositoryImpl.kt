package <%= appPackage %>.data.repository

import <%= appPackage %>.data.cache.Cache
import <%= appPackage %>.data.model.CommentEntity
import <%= appPackage %>.data.model.CommentMapper
import <%= appPackage %>.data.remote.CommentsApi
import <%= appPackage %>.domain.model.Comment
import <%= appPackage %>.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepositoryImpl @Inject constructor(private val api: CommentsApi,
                                                private val cache: Cache<List<CommentEntity>>,
                                                private val mapper: CommentMapper) : CommentRepository {
    override val key = "Comment List"

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> = when (refresh) {
        true -> api.getComments(postId).flatMap { set(postId, it) }.map { mapper.mapToDomain(it) }
        false -> cache.load(key + postId).map { mapper.mapToDomain(it) }.onErrorResumeNext { get(postId, true) }
    }

    private fun set(postId: String, list: List<CommentEntity>) = cache.save(key + postId, list)
}
