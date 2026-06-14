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

    api(project(":traveler"))
    api(project(":paper:core"))
    api(project(":dhl"))
    api(project(":paper:dhl"))
    api(project(":dhl:shared"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
        assemble {
            dependsOn(shadowJar)
        }
        shadowJar {
            dependsOn(":dhl:shadowJar")
            dependsOn(":paper:core:shadowJar")

            relocate("com.mojang.authlib", "net.crystopia.libs.authlib")
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
                artifactId = "panic"
                groupId = group as String
                version = version


            }
        }
    }
}

