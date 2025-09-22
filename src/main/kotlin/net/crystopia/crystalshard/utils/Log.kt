package net.crystopia.crystalshard.utils

import net.crystopia.crystalshard.api.Log
import java.text.SimpleDateFormat
import java.util.*


/**
 * 
 * CrystalShard Logger (Basic)
 *
 */
object Log : Log {

    override fun getTimestamp(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date())
    }

    override fun warn(message: String) {
        println("${YELLOW}[Log] $message$RESET")
    }

    override fun error(message: String) {
        println("${RED}[Log] $message$RESET")
    }

    override fun info(message: String) {
        println("${BLUE}[Log] $message$RESET")
    }

    override fun log(message: String) {
        println("[Log] $message$RESET")
    }

    override fun debug(message: String) {
        println("${MAGENTA}[Log] $message$RESET")
    }
}
