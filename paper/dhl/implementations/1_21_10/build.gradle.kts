import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar.Companion.shadowJar

plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev")
    id("com.gradleup.shadow") version "9.2.2"
}

group = "net.crystopia.crystalshard.paper.dhl.versions"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

dependencies {
    // Paper
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")
    implementation(project(":paper:dhl:shared"))
}

tasks {
    tasks {
        assemble {
            dependsOn(shadowJar)
            dependsOn(reobfJar)
        }
        java {
            withSourcesJar()
            withJavadocJar()
        }
    }
}