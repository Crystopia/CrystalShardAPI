plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization")
    id("com.gradleup.shadow") version "9.2.2"
}

dependencies {

    // Database
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("eu.vendeli:rethis:0.3.2")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // ENV
    implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")

    // kaml
    implementation("com.charleskorn.kaml:kaml:0.96.0")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        relocate("org.jetbrains.kotlinx", "net.crystopia.libs.kotlinx")
        relocate("com.charleskorn.kaml", "net.crystopia.libs.kaml")
        relocate("org.ktorm.ktorm-core", "net.crystopia.libs.ktorm")
        relocate("eu.vendeli.rethis", "net.crystopia.libs.rethis")
        relocate("io.github.cdimascio.dotenv-kotlin", "net.crystopia.libs.dotenv")
    }
}