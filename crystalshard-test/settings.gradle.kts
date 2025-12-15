pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include("crystalshard-test")

rootProject.name = "CrystalShardAPITest"