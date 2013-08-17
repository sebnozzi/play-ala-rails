package daos

import models.Post
import models.User
import models.AppSchema._

import org.squeryl.PrimitiveTypeMode._

object PostDao {

  def createPost(user: User, text: String): Post = {
    inTransaction {
      posts.insert(Post(user.id, text))
    }
  }

  def findForUser(user: User): List[Post] = {
    inTransaction {
      user.posts.toList
    }
  }

}