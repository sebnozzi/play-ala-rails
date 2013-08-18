import sbt._
import Keys._
import play.Project._
import templemore.sbt.cucumber.CucumberPlugin

object ApplicationBuild extends Build {

  val appName         = "PlayCucumber"
  val appVersion      = "1.0-SNAPSHOT"

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"  
  val activeRecord = "com.github.aselab" %% "scala-activerecord" % "0.2.2"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.12"    
  val flyway = "com.googlecode.flyway" % "flyway-core" % "2.1.1"
  
  val cucumberScala =  "info.cukes" % "cucumber-scala" % "1.1.2" % "test"
  
  val phantomJsDriver = "com.github.detro.ghostdriver" % "phantomjsdriver" % "1.0.3" % "test"  
  
  val appDependencies = Seq(
    // Add your project dependencies here
    scalaTest,
    activeRecord,
    mysql,
    phantomJsDriver,
    flyway,
    cucumberScala,
    jdbc
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
