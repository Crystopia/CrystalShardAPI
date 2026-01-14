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
include(":paper:dhl")
include(":paper:dhl:implementations:1_21_1")
include(":paper:dhl:implementations:1_21_10")
include(":paper:dhl:shared")
include(":paper:box")
include(":paper:pack")
include(":paper:simulacrum")
include(":paper:panic")
include(":paper:custom")
include(":tests:papertest")
include(":tests:velocitytest")

rootProject.name = "CrystalShardAPI"
