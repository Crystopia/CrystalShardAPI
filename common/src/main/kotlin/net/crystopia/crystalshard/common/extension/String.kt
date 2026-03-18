package net.crystopia.crystalshard.common.extension

import java.math.BigInteger
import java.util.*

// Inspiration from https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/string/String.kt

fun String.toSmallText(): String {
    var string = this
    return string.uppercase()
        .replace("Q", "ꞯ")
        .replace("W", "ᴡ")
        .replace("E", "ᴇ")
        .replace("R", "ʀ")
        .replace("T", "ᴛ")
        .replace("Y", "ʏ")
        .replace("U", "ᴜ")
        .replace("I", "ɪ")
        .replace("O", "ᴏ")
        .replace("P", "ᴘ")
        .replace("A", "ᴀ")
        .replace("S", "ѕ")
        .replace("D", "ᴅ")
        .replace("F", "ꜰ")
        .replace("G", "ɢ")
        .replace("H", "ʜ")
        .replace("J", "ᴊ")
        .replace("K", "ᴋ")
        .replace("L", "ʟ")
        .replace("Z", "ᴢ")
        .replace("X", "x")
        .replace("C", "ᴄ")
        .replace("V", "ᴠ")
        .replace("B", "ʙ")
        .replace("N", "ɴ")
        .replace("M", "ᴍ")
}

fun String.toUUID(): UUID {
    if (contains("-")) return UUID.fromString(this)
    return UUID(BigInteger(substring(0, 16), 16).toLong(), BigInteger(substring(16, 32), 16).toLong())
}

var CASE_DELIMITER_REGEX = Regex("(?<!^)(?=[A-Z])|[_\\-\\s]+")

enum class Case {
    CAMEL,
    SNAKE,
    KEBAB,
    PASCAL,
}


fun String.formatCase(case: Case): String = CASE_DELIMITER_REGEX.split(this)
    .filter { it.isNotEmpty() }
    .map { it.lowercase() }
    .run {
        when (case) {
            Case.CAMEL -> mapIndexed { index, word ->
                if (index == 0) word else word.capitalizeFirstLetter()
            }.joinToString("")

            Case.SNAKE -> joinToString("_")

            Case.KEBAB -> joinToString("-")

            Case.PASCAL -> joinToString("") { it.capitalizeFirstLetter() }
        }
    }

/**
 * Capitalizes the first letter of a string.
 *
 * If the first character of the string is lowercase, it is converted to uppercase. Otherwise, the string is returned unchanged.
 *
 * @return The string with its first letter capitalized.
 */
fun String.capitalizeFirstLetter(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}