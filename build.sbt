name := "sparkassignment"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.1.4",
  "org.apache.spark" % "spark-core_2.11" % "2.2.0",
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0",
  "org.apache.spark" % "spark-streaming_2.11" % "2.2.0",
  "com.typesafe" % "config"% "1.3.1"
)

dependencyOverrides += "com.google.guava" % "guava" % "11.0.2"
dependencyOverrides += "commons-net" % "commons-net" % "2.2"
dependencyOverrides += "io.netty" % "netty" % "3.9.9.Final"
