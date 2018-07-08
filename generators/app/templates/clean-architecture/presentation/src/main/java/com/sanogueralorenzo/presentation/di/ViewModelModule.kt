package <%= appPackage %>.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import <%= appPackage %>.presentation.ViewModelFactory
import <%= appPackage %>.presentation.ViewModelKey
import <%= appPackage %>.presentation.postdetails.PostDetailsViewModel
import <%= appPackage %>.presentation.postlist.PostListViewModel
import <%= appPackage %>.presentation.userdetails.UserDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    internal abstract fun postListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailsViewModel::class)
    internal abstract fun postDetailsViewModel(viewModel: PostDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    internal abstract fun userDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel

    //Add ViewModels here

}
