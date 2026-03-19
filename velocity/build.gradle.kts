plugins {
    kotlin("jvm")
    id("com.gradleup.shadow")
    id("maven-publish")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")
}

kotlin {
    jvmToolchain(22)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
    }
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            url = uri("https://repo.xyzify.ing/releases")
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
            artifactId = "velocity"
            groupId = group as String
            version = version
        }
    }
}