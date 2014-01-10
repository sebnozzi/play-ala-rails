import sbt._
import Keys._
import play.Project._
import templemore.sbt.cucumber.CucumberPlugin

object ApplicationBuild extends Build {

  val appName = "PlayAlaRails"
  val appVersion = "1.0-SNAPSHOT"

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  val activeRecord = "com.github.aselab" %% "scala-activerecord" % "0.2.3"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.12"
  val flyway = "com.googlecode.flyway" % "flyway-core" % "2.3.1"

  val cucumberScala = "info.cukes" % "cucumber-scala_2.10" % "1.1.5" % "test"  
  
  val prettyTime = "org.ocpsoft.prettytime" % "prettytime" % "3.0.2.Final"
  
  val phantomJsDriver = "com.github.detro.ghostdriver" % "phantomjsdriver" % "1.1.0" % "test"

  val appDependencies = Seq(
    // Add your project dependencies here
    scalaTest,
    activeRecord,
    mysql,
    phantomJsDriver,
    prettyTime,
    flyway,
    cucumberScala,
    jdbc)

  val cucumberSettings = CucumberPlugin.cucumberSettings ++
    Seq(
      CucumberPlugin.cucumberFeaturesLocation := "./test/features",
      CucumberPlugin.cucumberStepsBasePackage := "steps")

  val consoleInitialStatements = """
    |import play.core.StaticApplication
    |val app =  new StaticApplication(new java.io.File("."))
    |import models._
    |import com.github.aselab.activerecord.{ views => aviews, _ }
    |import dsl._
    |println("Play application initialized. Models imported.")
    """.stripMargin
   
  val mySettings = Seq(
      initialCommands in console := consoleInitialStatements,
      resolvers += "Sonatype-Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    ) ++ cucumberSettings

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    mySettings: _*)

}
