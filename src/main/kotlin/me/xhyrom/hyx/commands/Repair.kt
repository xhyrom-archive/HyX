package me.xhyrom.hyx.commands

import me.xhyrom.hylib.libs.commandapi.executors.PlayerCommandExecutor
import me.xhyrom.hyx.HyX.Companion.getInstanceUnsafe
import me.xhyrom.hyx.structs.Command
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.Damageable

class Repair : Command("repair") {
    init {
        command
            .withPermission("hyx.command.repair")
            .executesPlayer(PlayerCommandExecutor { sender, args ->
                this.execute(sender) { this.onDefault(sender as Player, args) }
            })
    }

    private fun onDefault(player: Player, args: Array<Any>) {
        val item = player.inventory.itemInMainHand
        val meta = item.itemMeta
        (meta as Damageable).damage = 0

        item.itemMeta = meta

        player.sendMessage(
            MiniMessage.miniMessage().deserialize(
                getInstanceUnsafe().lang().getString(
                    "commands.repair.message"
                ).get()
            )
        )
    }
}