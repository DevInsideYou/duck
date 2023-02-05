import Dependencies.{ io, _ }

ThisBuild / organization := "dev.insideyou"
ThisBuild / scalaVersion := "3.2.1"

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-explain",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Yexplicit-nulls", // experimental (I've seen it cause issues with circe)
    "-Ykind-projector",
    "-Ysafe-init", // experimental (I've seen it cause issues with circe)
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future-migration")

lazy val `duck` =
  project
    .in(file("."))
    .settings(name := "duck")
    .settings(commonSettings)
    .settings(dependencies)

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions --= Seq(
      "-Wunused:_",
      "-Xfatal-warnings",
    ),
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    eu.timepit.refined,
    io.github.arainko.ducktape,
    org.typelevel.`cats-core`,
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-17`,
  ).map(_ % Test),
)
