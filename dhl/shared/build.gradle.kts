plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm")
    id("io.papermc.paperweight.userdev")
    id("com.gradleup.shadow")
}

group = "net.crystopia.crystalshard.dhl"

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")
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
            artifactId = "shared"
            groupId = group as String
            version = version
        }
    }
}