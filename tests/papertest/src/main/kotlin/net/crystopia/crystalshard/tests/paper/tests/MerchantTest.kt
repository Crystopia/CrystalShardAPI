package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.openScreen
import net.crystopia.crystalshard.paper.dhl.packets.client.setMerchantOffer
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.ItemCost
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffer
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffers
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MerchantTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {
            val result = ItemStack(Material.DIRT)
            val resultMeta = result.itemMeta
            resultMeta.displayName(Component.text().text("<gray>!REICHHEITS STATUS!</gray>").build())

            result.itemMeta = resultMeta

            val baseCost = ItemStack(Material.EMERALD_BLOCK)

            ClientPacketFactory.openScreen(
                3243443,
                Component.text("..."),
                net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType.MERCHANT
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

            ClientPacketFactory.setMerchantOffer(
                windowId = 3243443,
                merchantOffers = MerchantOffers(
                    offers = mutableListOf(

                        MerchantOffer(
                            baseCost = ItemCost(
                                itemStack = baseCost,
                                count = 5
                            ),
                            optionalCost = null,
                            result = result,
                            uses = 19,
                            maxUses = 20,
                            experience = 1,
                            priceMultiplier = 2F,
                            demand = 0,
                            specialPrice = 4,
                            ignoreDiscounts = false,
                            experienceReward = false
                        )

                    )
                ),
                levelProgress = 1,
                experience = 3,
                leveled = true,
                refreshable = false
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

        }
    }

}