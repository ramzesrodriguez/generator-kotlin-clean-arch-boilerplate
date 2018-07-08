@file:Suppress("IllegalIdentifier")

package <%= appPackage %>.presentation.postdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import <%= appPackage %>.domain.usecase.CombinedUserPost
import <%= appPackage %>.domain.usecase.CommentsUseCase
import <%= appPackage %>.domain.usecase.UserPostUseCase
import <%= appPackage %>.presentation.*
import <%= appPackage %>.presentation.model.CommentItemMapper
import <%= appPackage %>.presentation.model.PostItemMapper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when` as _when

class PostDetailsViewModelTest {

    private lateinit var viewModel: PostDetailsViewModel

    private val mockUserPostUseCase = mock<UserPostUseCase> {}
    private val mockCommentsUseCase = mock<CommentsUseCase> {}

    private val postItemMapper = PostItemMapper()
    private val commentItemMapper = CommentItemMapper()

    private val combinedUserPost = CombinedUserPost(createUser(), createPost())
    private val comments = listOf(createComment())

    private val userId = "1"
    private val postId = "1"

    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PostDetailsViewModel(mockUserPostUseCase, mockCommentsUseCase, postItemMapper, commentItemMapper)
    }

    @Test
    fun `get post succeeds`() {
        // given
        _when(mockUserPostUseCase.get(userId, postId, false)).thenReturn(Single.just(combinedUserPost))
        _when(mockCommentsUseCase.get(postId, false)).thenReturn(Single.just(comments))

        // when
        viewModel.userIdPostId = UserIdPostId(userId, postId)

        // then
        assertEquals(postItemMapper.mapToPresentation(combinedUserPost), viewModel.post.value)
    }

    @Test
    fun `get comments succeeds`() {
        // given
        _when(mockUserPostUseCase.get(userId, postId, false)).thenReturn(Single.just(combinedUserPost))
        _when(mockCommentsUseCase.get(postId, false)).thenReturn(Single.just(comments))

        // when
        viewModel.userIdPostId = UserIdPostId(userId, postId)

        // then
        assertEquals(Data(dataState = DataState.SUCCESS, data = commentItemMapper.mapToPresentation(comments), message = null), viewModel.comments.value)
    }

    @Test
    fun `get comments fails`() {
        // given
        _when(mockUserPostUseCase.get(userId, postId, false)).thenReturn(Single.just(combinedUserPost))
        _when(mockCommentsUseCase.get(postId, false)).thenReturn(Single.error(throwable))

        // when
        viewModel.userIdPostId = UserIdPostId(userId, postId)

        // then
        assertEquals(Data(dataState = DataState.ERROR, data = null, message = throwable.message), viewModel.comments.value)
    }
}
