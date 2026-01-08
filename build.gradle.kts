plugins {
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization") version "2.+" apply false
    id("java-library")
    id("maven-publish")
    id("xyz.jpenilla.run-velocity") version "3.+" apply false 
    id('xyz.jpenilla.run-paper') version: '3.+' apply false 
    id("com.gradleup.shadow") version "9.2.2" apply false
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19" apply false
    kotlin("kapt") version "2.3.0" apply false
}

allprojects {
    group = "net.crystopia"
    version = "0.2.22"

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
