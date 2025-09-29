plugins {
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.serialization") version "2.2.20"
    id("maven-publish")
}

group = "net.crystopia"
version = "0.2.1"

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
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")

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

tasks.create<Copy>("repleaceData") {
    filter { line: String ->
        line.replace("{version}", version.toString())
    }
}


tasks {
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
