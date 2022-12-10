package me.xhyrom.hyx.utils

import me.xhyrom.hyx.HyX
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import java.util.*

object Vanish {
    val vanished = mutableListOf<UUID>()
    val vanishedBossbar = Bukkit.createBossBar("§c§lVanished", BarColor.RED, BarStyle.SOLID)

    fun isVanished(player: Player): Boolean {
        return vanished.contains(player.uniqueId)
    }

    fun setVanished(player: Player, vanished: Boolean, loop: Boolean = true) {
        if (vanished) {
            this.vanished.add(player.uniqueId)
            vanishedBossbar.addPlayer(player)

            if (loop) hidePlayer(player)
        } else {
            this.vanished.remove(player.uniqueId)
            vanishedBossbar.removePlayer(player)

            if (loop) showPlayer(player)
        }
    }

    fun hidePlayer(player: Player) {
        for (onlinePlayer in player.server.onlinePlayers) {
            if (!onlinePlayer.hasPermission("hyx.command.vanish.see")) {
                onlinePlayer.hidePlayer(HyX.getInstance(), player)
            }
        }
    }

    fun showPlayer(player: Player) {
        for (onlinePlayer in player.server.onlinePlayers) {
            onlinePlayer.showPlayer(HyX.getInstance(), player)
        }
    }
}