plugins {
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.serialization") version "2.2.20"
    id("com.gradleup.shadow") version "8.3.+"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    id("maven-publish")
}

group = "net.crystopia"
version = "0.2.8"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.xyzhub.link/releases")
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
}

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
    // compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")

    // AuthLib
    implementation("com.mojang:authlib:3.13.56")

    // Twilight
    implementation("gg.flyte:twilight:1.1.22")
    
    // Database
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("eu.vendeli:rethis:0.3.2")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // kaml
    implementation("com.charleskorn.kaml:kaml:0.96.0")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    assemble {
        dependsOn(shadowJar)
        dependsOn(reobfJar)
    }
    shadowJar {
        manifest {
            attributes["paperweight-mappings-namespace"] = "mojang"
        }

        relocate("com.charleskorn.kaml", "net.crystopia.libs.kaml")
        relocate("com.mojang.authlib", "net.crystopia.libs.authlib")
        relocate("org.ktorm.ktorm-core", "net.crystopia.libs.ktorm")
        relocate("eu.vendeli.rethis", "net.crystopia.libs.rethis")
        relocate("gg.flyte.twilight", "net.crystopia.libs.twilight")
    }
    build {
    }
    java {
        withSourcesJar()
        withJavadocJar()
    }
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            url = uri("https://repo.xyzhub.link/releases")
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
            artifactId = "crystalshard"
            groupId = group as String
            version = version
        }
    }
}
