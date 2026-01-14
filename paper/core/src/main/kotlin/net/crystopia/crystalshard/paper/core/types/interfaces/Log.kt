package net.crystopia.crystalshard.paper.core.types.interfaces

import java.text.SimpleDateFormat
import java.util.Date

/**
 *
 * Interface for the Logger of CrystalShard to make your own one.
 *
 */
interface Log {

    fun getTimestamp(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date())
    }

    val RESET: String
        get() = "\u001B[0m"

    val GRAY: String
        get() = "\\033[1;30m"

    val WHITE: String
        get() = "\\033[1;37m"

    val RED: String
        get() = "\u001B[31m"

    val GREEN: String
        get() = "\u001B[32m"

    val LIGHTGREEN: String
        get() = "\\033[1;32m"

    val YELLOW: String
         get() = "\u001B[33m"
    val BLUE: String
        get() = "\u001B[34m"
    val MAGENTA: String
        get() = "\u001B[35m"

    fun warn(message: String) {
        println("${YELLOW} $message$RESET")
    }

    fun error(message: String) {
        println("${RED}$message$RESET")
    }

    fun info(message: String) {
        println("${BLUE}$message$RESET")
    }

    fun log(message: String) {
        println("$message$RESET")
    }

    fun debug(message: String) {
        println("${MAGENTA}$message$RESET")
    }
}