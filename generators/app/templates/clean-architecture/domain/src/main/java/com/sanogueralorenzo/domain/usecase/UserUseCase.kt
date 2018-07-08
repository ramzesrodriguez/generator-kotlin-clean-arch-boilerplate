package <%= appPackage %>.domain.usecase

import <%= appPackage %>.domain.model.User
import <%= appPackage %>.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {

    fun get(userId: String, refresh: Boolean): Single<User> = repository.get(userId, refresh)
}
