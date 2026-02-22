package net.crystopia.crystalshard.paper.box

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player


fun tablist(header: Component? = null, footer: Component? = null, callback: TabList.() -> Unit): TabList {
    val list = TabList(
        header,
        footer
    )

    callback.invoke(list)
    return list
}

class TabList {

    private var header: net.kyori.adventure.text.Component = Component.text("")
    private var footer: net.kyori.adventure.text.Component = Component.text("")
    private var players: MutableList<Player> = mutableListOf()

    constructor(header: Component? = null, footer: Component? = null) {
        if (header != null) this.header = header
        if (footer != null) this.footer = footer
    }

    fun update() {
        listOfNotNull(players)
        applyToClient()
    }

    fun players(players: MutableList<Player>) {
        this.players = players
        applyToClient()
    }

    fun footer(footer: Component) {
        this.footer = footer
        applyToClient()
    }

    fun header(header: Component) {
        this.header = header
        applyToClient()
    }

    private fun applyToClient() {
        ClientPacketFactory.setTabList(
            footer, header
        ) { packet ->
            packet.send(players)
        }
    }

}