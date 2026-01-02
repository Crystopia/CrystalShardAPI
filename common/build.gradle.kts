plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization")
    id("io.papermc.paperweight.userdev")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")

    // Twilight
    implementation("gg.flyte:twilight:1.1.22")

    // Database
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("eu.vendeli:rethis:0.3.2")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ENV
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // Command API
    compileOnly("dev.jorel:commandapi-paper-core:11.0.0")
    implementation("dev.jorel:commandapi-paper-shade:11.0.0")
    implementation("dev.jorel:commandapi-kotlin-paper:11.0.0")

    // kaml
    implementation("com.charleskorn.kaml:kaml:0.96.0")
    
    implementation(project(":shared"))
}