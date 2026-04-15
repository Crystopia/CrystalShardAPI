package net.crystopia.crystalshard.common.log

fun String.red(): String {
    return "${Log.RED}${this}${Log.RESET}"
}

fun String.reset(): String {
    return "${Log.RESET}${this}${Log.RESET}"
}

fun String.blue(): String {
    return "${Log.BLUE}${this}${Log.RESET}"
}

fun String.gray(): String {
    return "${Log.GRAY}${this}${Log.RESET}"
}

fun String.white(): String {
    return "${Log.WHITE}${this}${Log.RESET}"
}

fun String.green(): String {
    return "${Log.GREEN}${this}${Log.RESET}"
}

fun String.lightgreen(): String {
    return "${Log.LIGHTGREEN}${this}${Log.RESET}"
}

fun String.yellow(): String {
    return "${Log.YELLOW}${this}${Log.RESET}"
}

fun String.magenta(): String {
    return "${Log.MAGENTA}${this}${Log.RESET}"
}