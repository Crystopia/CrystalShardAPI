package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos

data class ClientboundBlockDestructionPacketData(
   var entityId: Int,var  pos: BlockPos,
   /**
    * Read more: [Packets#Block_Entity_Data](https://minecraft.wiki/w/Java_Edition_protocol/Packets#Block_Entity_Data)
    */
   var progress : Int
)
