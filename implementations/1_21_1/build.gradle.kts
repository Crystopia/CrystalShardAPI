plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
    implementation(project(":shared"))
}

