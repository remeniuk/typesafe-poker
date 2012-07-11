scalaVersion := "2.9.1"

scalacOptions += "-Ydependent-method-types"

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "1.2.2"
)