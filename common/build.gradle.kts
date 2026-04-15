plugins {
    id("java-library")
    kotlin("jvm") version "2.3.+"
    kotlin("plugin.serialization")
    id("com.gradleup.shadow") version "9.2.2"
    id("maven-publish")
}

group = "net.crystopia.crystalshard"

dependencies {
    // Minimessage
    implementation("net.kyori:adventure-api:5.+")
    implementation("net.kyori:adventure-text-minimessage:5.+")
    implementation("net.kyori:adventure-text-serializer-plain:5.+")
    implementation("net.kyori:adventure-text-serializer-gson:5.+")
    implementation("net.kyori:adventure-text-serializer-legacy:5.+")

    // Database
    api("org.ktorm:ktorm-core:4.1.1")
    api("org.ktorm:ktorm-support-postgresql:4.1.1")
    api("org.ktorm:ktorm-support-mysql:4.1.1")
    api("org.postgresql:postgresql:42.7.2")
    api("eu.vendeli:rethis:0.3.2")

    // Kotlin
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ENV
    api("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // yamlkt
    api("net.mamoe.yamlkt:yamlkt:0.13.0")
}

kotlin {
    jvmToolchain(22)
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
                artifactId = "common"
                groupId = group as String
                version = version
            }
        }
    }
}

