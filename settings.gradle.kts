pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include(":shared")
include(":common")
include(":extras")
include(":implementations")
include(":implementations:1_21_1")
include(":implementations:1_21_10")
include(":tests")

rootProject.name = "CrystalShardAPI"