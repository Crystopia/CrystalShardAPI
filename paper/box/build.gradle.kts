plugins {
    id("java-library")
    kotlin("jvm")
    id("com.gradleup.shadow")
    id("io.papermc.paperweight.userdev")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "net.crystopia.crystalshard.paper"

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")

    compileOnly(project(":paper:core"))
    compileOnly(project(":paper:dhl:types"))
    compileOnly(project(":paper:dhl"))
    compileOnly(project(":dhl"))
    compileOnly(project(":dhl:shared"))
}

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            url = uri("https://repo.jespersen.zip/releases")
            credentials {
                username = System.getenv("REPOSILITE_USER") ?: System.getProperty("REPOSILITE_USER") ?: "USERNAME"
                password = System.getenv("REPOSILITE_TOKEN") ?: System.getProperty("REPOSILITE_TOKEN") ?: "TOKEN"
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("reposilite") {
            from(components["java"])
            artifactId = "box"
            groupId = group as String
            version = version
        }
    }
}