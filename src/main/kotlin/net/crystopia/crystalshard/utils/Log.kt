package net.crystopia.crystalshard.utils

import java.text.SimpleDateFormat
import java.util.*

object Log {

    private fun getTimestamp(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date())
    }

    private const val RESET = "\u001B[0m"
    private const val RED = "\u001B[31m"
    private const val GREEN = "\u001B[32m"
    private const val YELLOW = "\u001B[33m"
    private const val BLUE = "\u001B[34m"
    private const val MAGENTA = "\u001B[35m"

    fun warn(message: String) {
        println("${YELLOW}[CrystalBench] $message$RESET")
    }

    fun error(message: String) {
        println("${RED}[CrystalBench] $message$RESET")
    }

    fun info(message: String) {
        println("${BLUE}[CrystalBench] $message$RESET")
    }

    fun log(message: String) {
        println("[CrystalBench] $message$RESET")
    }

    fun debug(message: String) {
        println("${MAGENTA}[CrystalBench] $message$RESET")
    }

    fun logPluginLoading() {
        println(
            """
            
        """.trimIndent()
        )
    }
}
