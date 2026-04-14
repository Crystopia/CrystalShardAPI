package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.setEntityData
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3i
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.simulacrum.SimulacrumFactory
import net.crystopia.crystalshard.paper.simulacrum.displays.STextDisplay
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class DisplayTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            SimulacrumFactory.createDisPlayEntity<STextDisplay>(
                NamespacedKey("sdfds", "dfgdf"),
                EntityType.TEXT_DISPLAY,
                org.bukkit.Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
                mutableListOf(sender as Player)
            ) {
                ClientPacketFactory.setEntityData(
                    this.entity.entityId, mutableListOf(
                        EntityMetadata<Byte>(
                            index = 27,
                            type = EntityDataSerializerType.BYTE,
                            value = (0x01 or 0x02).toByte()
                        )
                    )
                ) { packet ->
                    packet.send(mutableListOf(sender as Player))
                }

                text(
                    Component.text().text("HEYY").build(), mutableListOf(sender as Player)
                )

                onInteract(
                    NamespacedKey("crystalshardtest", "playerjoindisplaydetect"),
                    CrystalShardPluginTest.instance,
                    Pair(2.0F, 2.0F),
                    sender as Player
                ) {
                    (sender as Player).sendMessage(
                        data.text!!
                    )
                }

                onHover(CrystalShardPluginTest.instance, sender as Player, 0.90) { isLockingAt ->
                    if (isLockingAt) {
                        // println("Lock: ${event.player.name}")


                        ClientPacketFactory.setEntityData(
                            entity.entityId, mutableListOf(
                                EntityMetadata(
                                    index = 12,
                                    type = EntityDataSerializerType.VECTOR3,
                                    value = Vec3i(2.0, 2.0, 2.0)
                                )
                            )
                        ) { packet ->
                            packet.send(mutableListOf(sender as Player))
                        }


                    } else {
                        // println("NotLock: ${event.player.name}")


                        ClientPacketFactory.setEntityData(
                            entity.entityId, mutableListOf(
                                EntityMetadata(
                                    index = 12,
                                    type = EntityDataSerializerType.VECTOR3,
                                    value = Vec3i(1.0, 1.0, 1.0)
                                )
                            )
                        ) { packet ->
                            packet.send(mutableListOf(sender as Player))
                        }


                    }

                }
            }
        }
    }

}