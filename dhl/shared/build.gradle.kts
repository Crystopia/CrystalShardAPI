plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm") version "2.3.+"
    id("com.gradleup.shadow") version "9.2.2"
}

group = "net.crystopia.crystalshard.dhl"

repositories {
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("com.mojang:brigadier:1.0.+")
    compileOnly(files("/home/xyzjesper/Dokumente/JVMLibs/26.1-net.minecraft.jar"))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
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
                artifactId = "shared"
                groupId = group as String
                version = version
            }
        }
    }
}