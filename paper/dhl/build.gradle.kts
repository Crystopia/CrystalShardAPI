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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("zip.jespersen:Kore.kt:0.1.2")

    compileOnly(project(":paper:core"))

    compileOnly(project(":dhl"))
    compileOnly(project(":dhl:shared"))
    compileOnly(project(":paper:dhl:types"))
    compileOnly(project(":dhl:implementations:1_21_1"))
    compileOnly(project(":dhl:implementations:1_21_9"))
    compileOnly(project(":dhl:implementations:1_21_10"))
    compileOnly(project(":dhl:implementations:1_21_11"))
    compileOnly(project(":paper:dhl:converter:1_21_1"))
    compileOnly(project(":paper:dhl:converter:1_21_9"))
    compileOnly(project(":paper:dhl:converter:1_21_10"))
    compileOnly(project(":paper:dhl:converter:1_21_11"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        dependsOn(":paper:core:shadowJar")
        dependsOn(":dhl:shadowJar")
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

