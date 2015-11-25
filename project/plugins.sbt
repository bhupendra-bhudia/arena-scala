addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.3")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "3.0.0")
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.2.0")

resolvers += "Artifactory Repository" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"
resolvers += "Sonatype Repository" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
