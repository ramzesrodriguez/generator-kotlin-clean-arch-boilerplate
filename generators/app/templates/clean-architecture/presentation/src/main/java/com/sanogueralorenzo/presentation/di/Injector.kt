package <%= appPackage %>.presentation.di

import <%= appPackage %>.presentation.postdetails.PostDetailsActivity
import <%= appPackage %>.presentation.postlist.PostListActivity
import <%= appPackage %>.presentation.userdetails.UserDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,  ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface Injector {
    fun inject(activity: PostListActivity)
    fun inject(activity: UserDetailsActivity)
    fun inject(activity: PostDetailsActivity)
}
