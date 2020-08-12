
/*
 * BASIC INFORMATION
 ********************************************************/

name := "ubirch-response-utils"
version := "0.5.1"
description := "HTTP Response Utils"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-response-utils"),
  "https://github.com/ubirch/ubirch-response-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}

/*
 * RESOLVER
 ********************************************************/

val resolverUbirchUtils = "ubirch.mycloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  resolverUbirchUtils)


/*
 * PUBLISHING
 ********************************************************/

publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true



/*
 * DEPENDENCIES
 ********************************************************/


// Versions
val akkaV = "2.5.11"
val akkaHttpV = "10.1.3"

// Groups
val akkaG = "com.typesafe.akka"
val ubirchUtilGroup = "com.ubirch.util"

//Ubirch dependencies
val ubirchUtilJson = ubirchUtilGroup %% "ubirch-json-utils" % "0.5.2"

//External dependencies
val akkaHttp = akkaG %% "akka-http" % akkaHttpV
val akkaStream = akkaG %% "akka-stream" % akkaV
val akkaHttpTestkit = akkaG %% "akka-http-testkit" % akkaHttpV
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

libraryDependencies ++= Seq(
  akkaHttp,
  akkaStream,
  ubirchUtilJson,
  akkaHttpTestkit % "test",
  scalaTest % "test"
)
