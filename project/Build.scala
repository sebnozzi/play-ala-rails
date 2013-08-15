import sbt._
import Keys._
import play.Project._
import templemore.sbt.cucumber.CucumberPlugin

object ApplicationBuild extends Build {

  val appName         = "PlayCucumber"
  val appVersion      = "1.0-SNAPSHOT"

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"  
  
  val appDependencies = Seq(
    // Add your project dependencies here
    scalaTest,
    // Play's default dependencies
    jdbc,
    anorm
  )

  val cucumberSettings = CucumberPlugin.cucumberSettings ++
    Seq(
        CucumberPlugin.cucumberFeaturesLocation := "./test/features",
        CucumberPlugin.cucumberStepsBasePackage := "steps")

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    cucumberSettings: _*
  )

}
