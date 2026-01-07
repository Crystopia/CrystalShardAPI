plugins {
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("xyz.jpenilla.run-paper") version "3.+"
    id("com.gradleup.shadow")
    id("io.papermc.paperweight.userdev")
    id("maven-publish")
}

group = "net.crystopia"

dependencies {
    // Paper
    
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")

    // Twilight
    implementation("gg.flyte:twilight:1.1.22")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Command API
    compileOnly("dev.jorel:commandapi-paper-core:11.0.0")
    implementation("dev.jorel:commandapi-paper-shade:11.0.0")
    implementation("dev.jorel:commandapi-kotlin-paper:11.0.0")
    
    // Crystalshard
    implementation(project(":"))
    implementation(project(":shared"))
    implementation(project(":common"))
    implementation(project(":paper"))
    implementation(project(":implementations:1_21_1"))
    implementation(project(":implementations:1_21_10"))
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
