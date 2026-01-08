plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")
    implementation(project(":paper:shared"))
}
