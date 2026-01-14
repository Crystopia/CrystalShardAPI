plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("xyz.jpenilla.run-velocity") version "3.+"
    id("com.gradleup.shadow")
}

dependencies {
    // Crystalshard
    implementation(project(":common"))
    implementation(project(":velocity"))

    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

kotlin {
    jvmToolchain(22)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    runVelocity {
        velocityVersion("3.4.0-SNAPSHOT")
    }
}