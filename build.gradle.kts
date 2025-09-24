plugins {
    kotlin("jvm") version "2.0.20-Beta1"
    kotlin("plugin.serialization") version "2.1.0"
    id("maven-publish")
}

group = "net.crystopia"
version = "0.1.3"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
}

dependencies {
    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")

    implementation("org.ktorm:ktorm-core:4.1.1")

}

kotlin {
    jvmToolchain(21)
}

tasks.create<Copy>("repleaceData") {
    filter { line: String ->
        line.replace("{version}", version.toString())
    }
}


tasks.build {

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
