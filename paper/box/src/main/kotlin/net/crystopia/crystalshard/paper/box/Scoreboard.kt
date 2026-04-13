package net.crystopia.crystalshard.paper.box

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.resetScoreInDisplayObject
import net.crystopia.crystalshard.paper.dhl.packets.client.sendObjectiveUpdate
import net.crystopia.crystalshard.paper.dhl.packets.client.setDisplayObjective
import net.crystopia.crystalshard.paper.dhl.packets.client.setScoreInDisplayObject
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.*
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.*
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

fun packetBoard(id: NamespacedKey, name: Component, slot: DisplaySlot, callback: Scoreboard.() -> Unit): Scoreboard {
    val board = Scoreboard(id, name, slot)
    callback.invoke(board)
    return board
}

class Scoreboard {

    private var id: NamespacedKey
    private var slot: DisplaySlot
    private var data: DisplayData<*>
    private var players: MutableList<Player> = mutableListOf()

    constructor(id: NamespacedKey, name: Component, slot: DisplaySlot) {
        this.id = id
        this.slot = slot
        this.data = DisplayData(
            name = id.toString(),
            displayName = name,
            displayAutoUpdate = false,
            numberFormat = NumberFormat.FIXED,
            format = FixedFormatData(name),
            renderType = RenderType.INTEGER,
            criteria = ObjectiveCriteria.DUMMY
        )

        ClientPacketFactory.setDisplayObjective(
            this.slot,
            this.data
        ) { packet ->
            packet.send(this.players)
        }
    }

    fun <T : FormatData<*>> format(type: NumberFormat, format: FormatData<*>): Scoreboard {

        this.data = DisplayData(
            name = id.toString(),
            displayName = this.data.displayName,
            displayAutoUpdate = this.data.displayAutoUpdate,
            numberFormat = type,
            format = format as T,
            renderType = this.data.renderType,
            criteria = this.data.criteria
        )
        update()
        return this
    }

    fun criteria(renderType: RenderType, criteria: ObjectiveCriteria): Scoreboard {
        this.data.criteria = criteria
        this.data.renderType = renderType
        update()
        return this
    }

    fun displayName(displayName: Component): Scoreboard {
        this.data.displayName = displayName
        this.data.displayAutoUpdate = false
        update()
        return this
    }

    fun displayName(displayName: Component, displayAutoUpdate: Boolean): Scoreboard {
        this.data.displayName = displayName
        this.data.displayAutoUpdate = displayAutoUpdate
        update()
        return this
    }

    fun players(player: MutableList<Player>): Scoreboard {
        this.players = player
        return this
    }

    fun players(player: Player): Scoreboard {
        this.players.add(player)
        return this
    }

    fun display(display: DisplayData<*>): Scoreboard {
        this.data = display
        this.data.name = this.id.toString()
        update()
        return this
    }

    fun slot(slot: DisplaySlot): Scoreboard {
        this.slot = slot
        update()
        return this
    }

    fun <T : FormatData<*>> score(
        ownerName: String,
        score: Int,
        displayName: Component,
        numberFormat: NumberFormat,
        format: FormatData<*>,
    ) : Scoreboard {
        ClientPacketFactory.setScoreInDisplayObject(
            ScoreData(
                id.toString(), ownerName, score, displayName, numberFormat, format as T
            )
        ) { packet ->
            packet.send(this.players)
        }
        return this
    }

    fun score(
        ownerName: String
    ): Scoreboard {
        ClientPacketFactory.resetScoreInDisplayObject(
            ScoreData(
                id.toString(), ownerName, 0, Component.text().build(), NumberFormat.BLANK, BlankFormatData()
            )
        ) { packet ->
            packet.send(this.players)
        }
        return this
    }

    fun remove(): Scoreboard {
        ClientPacketFactory.sendObjectiveUpdate(
            ScoreBoardMode.REMOVE,
            this.slot,
            data
        ) { packet ->
            packet.send(this.players)
        }
        return this
    }

    private fun update() {
        ClientPacketFactory.sendObjectiveUpdate(
            ScoreBoardMode.UPDATE,
            this.slot,
            this.data
        ) { packet ->
            packet.send(this.players)
        }
    }

}