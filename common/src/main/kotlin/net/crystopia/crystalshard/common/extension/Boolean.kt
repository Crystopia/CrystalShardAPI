package net.crystopia.crystalshard.common.extension

fun ifNull(expression: Any?): Boolean {
    return expression == null
}

fun ifNull(expression: Any?, callback: () -> Unit) {
    if (expression == null)
        callback()
}

fun ifNotNull(expression: Any?) : Boolean{
    return expression != null
}

fun ifNotNull(expression: Any?, callback: () -> Unit) {
    if (expression != null)
        callback()
}
