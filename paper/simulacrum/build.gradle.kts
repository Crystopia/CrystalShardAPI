plugins {
    id("java-library")
    kotlin("jvm")
    id("com.gradleup.shadow")
    id("io.papermc.paperweight.userdev")
    id("maven-publish")
}

group = "net.crystopia.crystalshard.paper"

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")

    api(project(":paper:core"))
    api(project(":paper:dhl"))
    api(project(":dhl"))
    api(project(":dhl:shared"))
    api(project(":dhl:implementations:1_21_1"))
    api(project(":dhl:implementations:1_21_9"))
    api(project(":dhl:implementations:1_21_10"))
    api(project(":dhl:implementations:1_21_11"))

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
                artifactId = "simulacrum"
                groupId = group as String
                version = version


            }
        }
    }
}

