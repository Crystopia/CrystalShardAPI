plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "net.crystopia.crystalshard.paper"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation(project(":common"))
    implementation(project(":paper:core"))
    implementation(project(":paper:dhl"))
}

kotlin {
    jvmToolchain(22)
}

tasks {
    assemble {
        dependsOn(shadowJar)
        dependsOn(reobfJar)
    }
    reobfJar {
        dependsOn(":paper:custom:jar")
    }
    shadowJar {
        dependsOn(":paper:core:shadowJar")
        dependsOn(":paper:dhl:shadowJar")
        archiveClassifier.set("")
        configurations = listOf(project.configurations["runtimeClasspath"])
        dependencies {
            include(dependency("net.crystopia.crystalshard:common"))
            include(dependency("net.crystopia.crystalshard.paper:core"))
            include(dependency("net.crystopia.crystalshard.paper:dhl"))
            include(dependency("net.crystopia.crystalshard.paper:shared"))
        }
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
                artifactId = "custom"
                groupId = group as String
                version = version
            }
        }
    }
}

