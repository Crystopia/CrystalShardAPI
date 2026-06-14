plugins {
    kotlin("jvm") version "2.4.+"
    kotlin("plugin.serialization") version "2.3.+" apply false
    id("java-library")
    id("maven-publish")
    id("xyz.jpenilla.run-velocity") version "3.+" apply false
    id("xyz.jpenilla.run-paper") version "3.+" apply false
    id("com.gradleup.shadow") version "9.+" apply false
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21" apply false
    kotlin("kapt") version "2.3.0" apply false
    id("org.jetbrains.dokka") version "2.1.0" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

allprojects {
    group = "net.crystopia.crystalshard"
    version = "1.2.7-SNAPSHOT-18"

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.jespersen.zip/releases")
    }
}