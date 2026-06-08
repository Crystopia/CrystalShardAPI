plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm")
    id("io.papermc.paperweight.userdev")
    id("com.gradleup.shadow")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

group = "net.crystopia.crystalshard.dhl"

repositories {
    maven("https://libraries.minecraft.net")
}
dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")
    implementation("com.mojang:brigadier:1.0.+")
}

paperweight {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

kotlin {
    jvmToolchain(25)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        relocate("com.mojang.authlib", "net.crystopia.libs.authlib")
    }
    java {
        withSourcesJar()
        withJavadocJar()
    }
    publishing {
        repositories {
            maven {
                name = "Reposilite"
                url = uri("https://repo.jespersen.zip/releases")
                credentials {
                    username = System.getenv("REPOSILITE_USER") ?: System.getProperty("REPOSILITE_USER") ?: "USERNAME"
                    password = System.getenv("REPOSILITE_TOKEN") ?: System.getProperty("REPOSILITE_TOKEN") ?: "TOKEN"
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
        publications {
            create<MavenPublication>("reposilite") {
                from(components["java"])
                artifactId = "shared"
                groupId = group as String
                version = version
            }
        }
    }
}