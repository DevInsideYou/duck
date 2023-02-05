import sbt._

object Dependencies {
  object com {
    object eed3si9n {
      object expecty {
        val expecty =
          "com.eed3si9n.expecty" %% "expecty" % "0.16.0"
      }
    }
  }

  object eu {
    object timepit {
      val refined =
        "eu.timepit" %% "refined" % "0.10.1"
    }
  }

  object io {
    object github {
      object arainko {
        val ducktape =
          "io.github.arainko" %% "ducktape" % "0.1.2"
      }
    }
  }

  object org {
    object scalatest {
      val scalatest =
        "org.scalatest" %% "scalatest" % "3.2.15"
    }

    object scalatestplus {
      val `scalacheck-1-17` =
        "org.scalatestplus" %% "scalacheck-1-17" % "3.2.15.0"
    }

    object typelevel {
      val `cats-core` =
        "org.typelevel" %% "cats-core" % "2.9.0"
    }
  }
}
