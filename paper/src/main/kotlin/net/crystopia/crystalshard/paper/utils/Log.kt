package net.crystopia.crystalshard.paper.utils

import net.crystopia.crystalshard.shared.interfaces.utils.Log
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
        println("${YELLOW}[WARN] $message$RESET")
    }

    override fun error(message: String) {
        println("${RED}[ERROR] $message$RESET")
    }

    override fun info(message: String) {
        println("${BLUE}[INFO] $message$RESET")
    }

    override fun log(message: String) {
        println("[Log] $message$RESET")
    }

    override fun debug(message: String) {
        println("${MAGENTA}[DEBUG] $message$RESET")
    }
}
