lazy val buildVersion =  "1.2.0"

organization := "se.radley"
description := "Scala Enumeration plugin for PlayFramework 2"
version := buildVersion
scalaVersion := "2.11.8"
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
libraryDependencies += "com.typesafe.play" %% "play" % "2.5.14" % "provided"
libraryDependencies += "com.typesafe.play" %% "play-test" % "2.5.14" % "test"
libraryDependencies += "org.specs2" %% "specs2" % "3.7" % "test"

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
pomExtra := (
  <url>http://github.com/leon/play-enumeration</url>
  <licenses>
    <license>
      <name>Apache 2.0</name>
      <url>http://www.opensource.org/licenses/Apache-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:leon/play-enumeration.git</url>
    <connection>scm:git:git@github.com:leon/play-enumeration.git</connection>
  </scm>
  <developers>
    <developer>
      <id>leon</id>
      <name>Leon Radley</name>
      <url>http://leon.radley.se</url>
    </developer>
  </developers>
)
publishTo := (version { version: String =>
  val nexus = "https://oss.sonatype.org/"
  if (version.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}).value
