package net.crystopia.crystalshard.api

import java.text.SimpleDateFormat
import java.util.*

interface Log {

    fun getTimestamp(): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date())
    }

    val RESET: String
        get() = "\u001B[0m"
    
    val RED: String
        get() = "\u001B[31m"
    
    val GREEN: String
        get() = "\u001B[32m"

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