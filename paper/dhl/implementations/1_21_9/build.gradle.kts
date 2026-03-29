import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar.Companion.shadowJar

plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev")
    id("com.gradleup.shadow") version "9.2.2"
    id("maven-publish")
}

group = "net.crystopia.crystalshard.paper.dhl.versions"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.9-R0.1-SNAPSHOT")
    implementation(project(":paper:dhl:shared"))
}

tasks {
    assemble {
        dependsOn(shadowJar)
        dependsOn(reobfJar)
    }
    shadowJar {
        dependsOn(":paper:dhl:shared:shadowJar")
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
                artifactId = "1_21_9"
                groupId = group as String
                version = version
            }
        }
    }
}