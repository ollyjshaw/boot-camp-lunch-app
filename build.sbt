name := """lunch_app"""
organization := "uk.co.shawthingtechnology"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

resolvers += Resolver.bintrayRepo("hmrc", "releases")

libraryDependencies += filters
libraryDependencies += "uk.gov.hmrc" %% "http-verbs" % "6.3.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "uk.co.shawthingtechnology.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "uk.co.shawthingtechnology.binders._"
