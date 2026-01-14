package net.crystopia.crystalshard.paper.dhl.nms

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.SoftwareType

interface INMSManager<T : Any> {

    val mcVersion: String

    val classVersion: String

    var handler: T

    fun setup(classPattern: String)
    
    fun software(): SoftwareType
    
}