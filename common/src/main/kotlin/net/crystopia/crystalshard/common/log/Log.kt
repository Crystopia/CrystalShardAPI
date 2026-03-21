package net.crystopia.crystalshard.common.log

import java.text.SimpleDateFormat
import java.util.Date

/**
 *
 * CrystalShard Logger (Basic)
 *
 */
object Log : ILog {

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
        println("[ILog] $message$RESET")
    }

    override fun debug(message: String) {
        println("${MAGENTA}[DEBUG] $message$RESET")
    }
}