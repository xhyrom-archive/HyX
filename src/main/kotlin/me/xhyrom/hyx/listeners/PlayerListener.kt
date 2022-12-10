package me.xhyrom.hyx.listeners

import me.xhyrom.hyx.HyX
import me.xhyrom.hyx.utils.Vanish
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (Vanish.isVanished(event.player)) Vanish.hidePlayer(event.player)

        for (player in Vanish.vanished) {
            Bukkit.getPlayer(player)?.let { event.player.hidePlayer(HyX.getInstance(), it) }
        }
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        if (Vanish.isVanished(event.player)) Vanish.setVanished(event.player, false, loop = false)
    }
}