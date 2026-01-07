plugins {
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("java-library")
    id("maven-publish")
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19" apply false
    kotlin("kapt") version "2.3.0" apply false
}

allprojects {
    group = "net.crystopia"
    version = "0.2.19"

    repositories {
        mavenCentral()
        mavenLocal()
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            url = uri("https://repo.xyzhub.link/releases")
        }
        maven("https://repo.flyte.gg/releases")
    }
}

dependencies {

    implementation(project(":common"))
    implementation(project(":paper"))
    implementation(project(":velocity"))
    implementation(project(":implementations:1_21_1"))
    implementation(project(":implementations:1_21_10"))

    /**
     * Extra Implementations
     */

    // Database
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("eu.vendeli:rethis:0.3.2")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ENV
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // kaml
    implementation("com.charleskorn.kaml:kaml:0.96.0")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        relocate("org.jetbrains.kotlinx", "net.crystopia.libs.kotlinx")
        relocate("com.charleskorn.kaml", "net.crystopia.libs.kaml")
        relocate("org.ktorm.ktorm-core", "net.crystopia.libs.ktorm")
        relocate("eu.vendeli.rethis", "net.crystopia.libs.rethis")
        relocate("io.github.cdimascio.dotenv-kotlin", "net.crystopia.libs.dotenv")
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