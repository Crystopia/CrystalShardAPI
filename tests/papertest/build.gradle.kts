plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("xyz.jpenilla.run-paper") version "3.+"
    id("com.gradleup.shadow")
    // id("io.papermc.paperweight.userdev")
    id("maven-publish")
}

// paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

group = "net.crystopia"

dependencies {
    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    // paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Command API
    compileOnly("dev.jorel:commandapi-paper-core:11.+")
    implementation("dev.jorel:commandapi-paper-shade:11.+")
    implementation("dev.jorel:commandapi-kotlin-paper:11.+")
    
    // Crystalshard
    implementation(project(":traveler"))
    implementation(project(":paper"))
    implementation(project(":paper:core"))
    implementation(project(":paper:folia"))
    implementation(project(":paper:box"))
    implementation(project(":paper:util"))
    implementation(project(":dhl"))
    implementation(project(":paper:dhl:types"))
    implementation(project(":dhl:shared"))
    implementation(project(":paper:dhl"))
    implementation(project(":paper:panic"))
    implementation(project(":paper:simulacrum"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    assemble {
        dependsOn(shadowJar)
        // dependsOn(reobfJar)
    }
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.11")
    }
}
