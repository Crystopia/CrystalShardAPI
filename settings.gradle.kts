pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include(":common")
include(":velocity")
include(":paper")
include(":paper:core")
include(":paper:shared")
include(":paper:implementations")
include(":paper:implementations:1_21_1")
include(":paper:implementations:1_21_10")
include(":tests:papertest")
include(":tests:velocitytest")

rootProject.name = "CrystalShardAPI"
include("paper:core")