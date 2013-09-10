Play à la Rails
===============

Sample Play app integrating some technologies in the attempt to make it more "Rails-like".

The technologies are:

* [ActiveRecord](https://github.com/aselab/scala-activerecord) (Scala implementation based on Squeryl)
* [Flyway](http://flywaydb.org/)
* [Cucumber-JVM](https://github.com/cucumber/cucumber-jvm) (through the [SBT plugin](https://github.com/skipoleschris/xsbt-cucumber-plugin))

Integration
-----------

The integration has been done **manually**, not using plugins (except for the Cucumber-SBT one).

Places to look for in order to understand the integration steps involved:

* Global object
* PlayCucumberSetup
* PlayCucumberEnvironment
* etc.

Setup / Dependencies
--------------------
**[PhantomJS](http://phantomjs.org/)** has to be installed in the system according to its website (executable has to be on the PATH).

The non-test version of the App uses MySql by default. Change the settings in `application.conf`


Cucumber
--------

In order to run cucumber tests, run "cucumber" in the sbt console.

```
  [play-ala-rails]$ play
  [PlayAlaRails] $ cucumber
```