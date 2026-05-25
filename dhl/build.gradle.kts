plugins {
    id("java-library")
    kotlin("jvm") version "2.3.+"
    id("com.gradleup.shadow") version "9.2.2"
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "net.crystopia.crystalshard"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation(project(":common"))
    implementation(project(":dhl:implementations:1_21_1"))
    implementation(project(":dhl:implementations:1_21_9"))
    implementation(project(":dhl:implementations:1_21_10"))
    implementation(project(":dhl:implementations:1_21_11"))
    implementation(project(":dhl:shared"))
}

tasks {
        assemble {
            dependsOn(shadowJar)
        }
        shadowJar {
            dependsOn(":paper:core:shadowJar")
            dependsOn(":dhl:shared:shadowJar")
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
                artifactId = "dhl"
                groupId = group as String
                version = version
            }
        }
    }
}

kotlin {
    jvmToolchain(22)
}