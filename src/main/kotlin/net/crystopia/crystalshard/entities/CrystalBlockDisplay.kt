package net.crystopia.crystalshard.entities

import org.bukkit.block.BlockType
import org.bukkit.entity.BlockDisplay
import org.bukkit.entity.Display


/**
 *
 * Better work with the Block Displays from Minecraft.
 *
 */
class CrystalBlockDisplay(val handle: BlockDisplay) : CrystalEntity(handle = handle) {

    fun blockDisplay(block: CrystalBlockDisplay.() -> Unit = {}) {
        CrystalBlockDisplay(handle)
    }
    
    fun setBlock(blockType: BlockType) {
        val block = blockType.createBlockData()
        handle.block = block
    }

    fun setBrightness(blockLight: Int, skyLight: Int) {
        handle.brightness = Display.Brightness(blockLight, skyLight)
    }

}