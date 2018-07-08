@file:Suppress("IllegalIdentifier")

package <%= appPackage %>.presentation.postlist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import <%= appPackage %>.domain.usecase.CombinedUserPost
import <%= appPackage %>.domain.usecase.UsersPostsUseCase
import <%= appPackage %>.presentation.*
import <%= appPackage %>.presentation.model.PostItemMapper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when` as _when

class PostListViewModelTest {

    private lateinit var viewModel: PostListViewModel

    private val mockUseCase = mock<UsersPostsUseCase> {}
    private val mapper = PostItemMapper()

    private val user = createUser()
    private val post = createPost()

    private val combinedUserPosts = listOf(CombinedUserPost(user, post))
    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `get post item list succeeds`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.just(combinedUserPosts))

        // when
        viewModel = PostListViewModel(mockUseCase, mapper)

        // then
        assertEquals(Data(dataState = DataState.SUCCESS, data = mapper.mapToPresentation(combinedUserPosts), message = null), viewModel.posts.value)
    }

    @Test
    fun `get post item list fails`() {
        // given
        _when(mockUseCase.get(false)).thenReturn(Single.error(throwable))

        // when
        viewModel = PostListViewModel(mockUseCase, mapper)

        // then
        assertEquals(Data(dataState = DataState.ERROR, data = null, message = throwable.message), viewModel.posts.value)
    }
}
