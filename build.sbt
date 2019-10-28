name := "sangria-slowlog"
organization := "org.sangria-graphql"
version := "0.1.9-SNAPSHOT"

description := "Sangria middleware to log slow GraphQL queries"
homepage := Some(url("http://sangria-graphql.org"))
licenses := Seq("Apache License, ASL Version 2.0" → url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.12.10"
crossScalaVersions := Seq("2.11.12", scalaVersion.value)

scalacOptions ++= Seq("-deprecation", "-feature")

scalacOptions ++= {
  if (scalaVersion.value startsWith "2.12")
    Seq.empty
  else
    Seq("-target:jvm-1.7")
}

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria" % "1.4.2",
  "io.dropwizard.metrics" % "metrics-core" % "4.0.7",
  "org.slf4j" % "slf4j-api" % "1.7.28",
  "io.opentracing.contrib" %% "opentracing-scala-concurrent" % "0.0.4",
  "io.opentracing" % "opentracing-mock" % "0.31.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.sangria-graphql" %% "sangria-json4s-native" % "1.0.0" % Test,
  "org.slf4j" % "slf4j-simple" % "1.7.28" % Test
)

// Publishing

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ ⇒ false)
publishTo := Some(
  if (version.value.trim.endsWith("SNAPSHOT"))
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
startYear := Some(2017)
organizationHomepage := Some(url("https://github.com/sangria-graphql"))
developers := Developer("OlegIlyenko", "Oleg Ilyenko", "", url("https://github.com/OlegIlyenko")) :: Nil
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/sangria-graphql/sangria-circe.git"),
  connection = "scm:git:git@github.com:sangria-graphql/sangria-circe.git"))

// nice *magenta* prompt!

shellPrompt in ThisBuild := { state ⇒
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}
