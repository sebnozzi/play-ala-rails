// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.1")


// Cucumber 
resolvers += "Templemore Repository" at "http://templemore.co.uk/repo"

// Cucumber
addSbtPlugin("templemore" % "sbt-cucumber-plugin" % "0.7.2")