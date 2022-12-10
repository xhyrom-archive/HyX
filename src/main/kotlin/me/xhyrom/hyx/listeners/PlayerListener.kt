package me.xhyrom.hyx.listeners

import me.xhyrom.hyx.HyX
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (HyX.getInstanceUnsafe().modules().vanish!!.isVanished(event.player))
            HyX.getInstanceUnsafe().modules().vanish!!.setVanished(event.player, true)

        for (player in HyX.getInstanceUnsafe().modules().vanish!!.vanished) {
            Bukkit.getPlayer(player)?.let { event.player.hidePlayer(HyX.getInstanceUnsafe(), it) }
        }
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player

        if (!HyX.getInstanceUnsafe().modules().vanish!!.isVanished(player)) return
        if (HyX.getInstanceUnsafe().modules().vanish!!.config.getBoolean("vanished-features.disable-damage").get())
            event.isCancelled = true
    }

    @EventHandler
    fun onTarget(event: EntityTargetEvent) {
        if (event.target !is Player) return
        val player = event.target as Player

        if (!HyX.getInstanceUnsafe().modules().vanish!!.isVanished(player)) return
        if (HyX.getInstanceUnsafe().modules().vanish!!.config.getBoolean("vanished-features.disable-entity-target").get())
            event.isCancelled = true
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player

        if (!HyX.getInstanceUnsafe().modules().vanish!!.isVanished(player)) return
        if (HyX.getInstanceUnsafe().modules().vanish!!.config.getBoolean("vanished-features.disable-damage").get())
            event.isCancelled = true
    }

    @EventHandler
    fun onFoodChange(event: FoodLevelChangeEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player

        if (!HyX.getInstanceUnsafe().modules().vanish!!.isVanished(player)) return
        if (
            HyX.getInstanceUnsafe().modules().vanish!!.config.getBoolean("vanished-features.disable-hunger").get() &&
            event.foodLevel <= player.foodLevel
        )
            event.isCancelled = true
    }
}