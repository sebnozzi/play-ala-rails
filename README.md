Play à la Rails
===============

Sample Play app integrating some technologies in the attempt to make it more "Rails-like".

The technologies are:

* ActiveRecord (Scala implementation based on Squeryl)
* Flyway
* Cucumber-JVM (through the SBT plugin)

Integration
-----------

The integration has been done **manually**, not using plugins (except for the Cucumber-SBT one).

Places to look for in order to understand the integration steps involved:

* Global object
* PlayCucumberSetup
* PlayCucumberEnvironment
* etc.

