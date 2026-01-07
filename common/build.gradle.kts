plugins {
    id("java-library")
    kotlin("jvm") version "2.+"
    kotlin("plugin.serialization")
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