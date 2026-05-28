package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.gui.EquipmentSlot
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createEquipment
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CreateEquipmentTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.createEquipment(
                entityId = player.entityId,
                equipmentList = mutableListOf(
                    Pair(EquipmentSlot.HEAD, ItemStack(Material.DIAMOND_HELMET)),
                    Pair(EquipmentSlot.CHEST, ItemStack(Material.DIAMOND_CHESTPLATE)),
                    Pair(EquipmentSlot.LEGS, ItemStack(Material.DIAMOND_LEGGINGS)),
                    Pair(EquipmentSlot.FEET, ItemStack(Material.DIAMOND_BOOTS)),
                    Pair(EquipmentSlot.MAINHAND, ItemStack(Material.DIAMOND_SWORD)),
                )
            ) { it.send(mutableListOf(player)) }
            println("CreateEquipment OK")
        }
    }
}