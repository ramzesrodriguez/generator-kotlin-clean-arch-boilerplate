package <%= appPackage %>.presentation.navigation

import android.app.Activity
import android.content.Intent
import <%= appPackage %>.presentation.model.POST_ID_KEY
import <%= appPackage %>.presentation.model.PostItem
import <%= appPackage %>.presentation.model.USER_ID_KEY
import <%= appPackage %>.presentation.postdetails.PostDetailsActivity
import <%= appPackage %>.presentation.userdetails.UserDetailsActivity
import javax.inject.Inject

class PostDetailsNavigator @Inject constructor() {

    fun navigate(activity: Activity, postItem: PostItem) {
        val intent = Intent(activity, PostDetailsActivity::class.java)
        intent.putExtra(USER_ID_KEY, postItem.userId)
        intent.putExtra(POST_ID_KEY, postItem.postId)
        activity.startActivity(intent)
    }
}

class UserDetailsNavigator @Inject constructor() {

    fun navigate(activity: Activity, userId: String) {
        val intent = Intent(activity, UserDetailsActivity::class.java)
        intent.putExtra(USER_ID_KEY, userId)
        activity.startActivity(intent)
    }
}