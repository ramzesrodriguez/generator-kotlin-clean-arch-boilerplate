package <%= appPackage %>.presentation.di

import <%= appPackage %>.data.repository.CommentRepositoryImpl
import <%= appPackage %>.data.repository.PostRepositoryImpl
import <%= appPackage %>.data.repository.UserRepositoryImpl
import <%= appPackage %>.domain.repository.CommentRepository
import <%= appPackage %>.domain.repository.PostRepository
import <%= appPackage %>.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl): PostRepository

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindCommentRepository(repository: CommentRepositoryImpl): CommentRepository
}
