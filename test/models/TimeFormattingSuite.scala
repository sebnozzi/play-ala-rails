package models

import org.scalatest.FunSuite
import models._
import java.sql.Timestamp

class TimeFormattingSuite extends FunSuite {

  def secondsAgo(secs:Int):Timestamp = {
    val now = new java.util.Date().getTime()
    new Timestamp(now - (secs*1000))
  }
  
  test("pretty time for creation") {
    val post = Post(text="hello") 
    post.createdAt = secondsAgo(60*5)
    assert(post.prettyTime === "5 minutes ago")
  }
  
  test("pretty time for modification") {
    val post = Post(text="hello") 
    post.createdAt = secondsAgo(1000)
    post.updatedAt = secondsAgo(1)
    assert(post.prettyTime === "moments ago")
  }
  
}