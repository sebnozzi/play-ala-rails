package daos

import models.Post
import models.User

import com.github.aselab.activerecord._
import dsl._

object PostDao {

  def createPost(user: User, text: String): Post = {
    val newPost = Post(text).create
    user.posts << newPost
  }

  def findAllForUser(user: User): List[Post] = {
    user.posts.toList
  }

}