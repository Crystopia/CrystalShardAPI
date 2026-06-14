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

    api(project(":dhl"))
    api(project(":dhl:shared"))
    api(project(":paper:dhl:types"))
    api(project(":dhl:implementations:1_21_1"))
    api(project(":dhl:implementations:1_21_9"))
    api(project(":dhl:implementations:1_21_10"))
    api(project(":dhl:implementations:1_21_11"))
    api(project(":paper:dhl:converter:1_21_1"))
    api(project(":paper:dhl:converter:1_21_9"))
    api(project(":paper:dhl:converter:1_21_10"))
    api(project(":paper:dhl:converter:1_21_11"))
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

