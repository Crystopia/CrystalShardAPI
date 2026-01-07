plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev")
    kotlin("plugin.serialization")
}

//paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")

    // Twilight
    implementation("gg.flyte:twilight:1.1.22")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    
    implementation(project(":shared"))
    implementation(project(":common"))
    implementation(project(":implementations:1_21_1"))
    implementation(project(":implementations:1_21_10"))
}

tasks {

    shadowJar {
        relocate("com.mojang.authlib", "net.crystopia.libs.authlib")
        relocate("gg.flyte.twilight", "net.crystopia.libs.twilight")
    }

}