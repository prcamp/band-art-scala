name := "band-art-scala2"

version := "0.1"

scalaVersion := "2.12.6"

classpathTypes ++= Set("jnilib")

//resolvers += MavenRepository("jogamp", "http://jogamp.org/deployment/maven")

libraryDependencies += "org.typelevel" %% "spire" % "0.14.1"

//libraryDependencies += "org.jogamp.jogl" % "jogl-all" % "2.3.2"

//libraryDependencies += "org.jogamp.jogl" % "newt" % "2.3.2"

//libraryDependencies += "org.jogamp.gluegen" % "gluegen-rt" % "2.3.2"
libraryDependencies += "org.processing" % "core" % "3.2.1"
//libraryDependencies ++= {
//  val architecture = Seq(
//    "natives-linux-amd64",
//    "natives-linux-armv6",
//    "natives-mac-osx"
//  )
// https://mvnrepository.com/artifact/org.jogamp.jogl/jogl
//libraryDependencies += "org.jogamp.jogl" % "jogl" % "2.3.2"
// https://mvnrepository.com/artifact/de.sojamo/oscp5
libraryDependencies += "de.sojamo" % "oscp5" % "0.9.8"

//  "org.jogamp.jogl" % "jogl-all" % "2.3.2" +: architecture.map(
//    "org.jogamp.jogl" % "jogl-all" % "2.3.2" classifier _
//  )
//}