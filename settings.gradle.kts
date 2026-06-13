pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include(":traveler")
include(":velocity")
include(":paper")
include(":paper:core")
include(":paper:folia")
include(":dhl")
include(":dhl:implementations:1_21_1")
include(":dhl:implementations:1_21_9")
include(":dhl:implementations:1_21_10")
include(":dhl:implementations:1_21_11")
include(":dhl:shared")
include(":paper:box")
include(":paper:dhl")
include(":paper:dhl:converter:1_21_1")
include(":paper:dhl:converter:1_21_9")
include(":paper:dhl:converter:1_21_10")
include(":paper:dhl:converter:1_21_11")
include(":paper:dhl:types")
include(":paper:simulacrum")
include(":paper:panic")
include(":paper:util")
include(":tests:papertest")
include(":tests:velocitytest")
include(":tests:modfabrictest")

rootProject.name = "CrystalShardAPI"