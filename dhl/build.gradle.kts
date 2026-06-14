plugins {
    id("java-library")
    kotlin("jvm")
    id("com.gradleup.shadow")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "net.crystopia.crystalshard"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.+")

    api(project(":dhl:implementations:1_21_1"))
    api(project(":dhl:implementations:1_21_9"))
    api(project(":dhl:implementations:1_21_10"))
    api(project(":dhl:implementations:1_21_11"))
    api(project(":dhl:shared"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    java {
        withSourcesJar()
        withJavadocJar()
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
                artifactId = "dhl"
                groupId = group as String
                version = version
            }
        }
    }
}