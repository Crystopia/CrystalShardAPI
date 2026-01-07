plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {

    implementation(project(":common"))

    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}
