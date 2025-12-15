plugins {
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("com.gradleup.shadow") version "9.+"
    id("xyz.jpenilla.run-paper") version "3.+"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("maven-publish")
}

group = "net.crystopia"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
}

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")

    // Crystalshard
    implementation("net.crystopia:crystalshard:0.2.9")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    assemble {
        dependsOn(shadowJar)
        dependsOn(reobfJar)
    }
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.10")
    }
}
