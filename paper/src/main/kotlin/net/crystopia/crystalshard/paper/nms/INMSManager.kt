package net.crystopia.crystalshard.extras.nms

import net.crystopia.crystalshard.shared.enums.server.SoftwareType

interface INMSManager<T : Any> {

    val mcVersion: String

    val classVersion: String

    var handler: T

    fun setup(classPattern: String)
    
    fun software(): SoftwareType
    
}