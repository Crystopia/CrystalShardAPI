package net.crystopia.crystalshard.nms

import net.crystopia.crystalshard.enums.SoftwareType

interface INMSManager<T : Any> {

    val mcVersion: String

    val classVersion: String

    var handler: T

    fun setup(classPattern: String)


    fun software(): SoftwareType

    fun isClass(className: String): Boolean
}