package net.crystopia.crystalshard.tests.paper.config

import kotlinx.serialization.Serializable
import net.mamoe.yamlkt.Comment

@Serializable
data class TestConfig(
    @Comment("This is a comment")
    var consoleMessage: String = "Hallo cool message from Config.",
    var test : String = "Test",
)
