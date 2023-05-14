package me.xhyrom.hyx.middlewares

import me.xhyrom.hyx.structs.Command
import org.bukkit.entity.Player
import java.util.UUID

object CooldownMiddleware {
    val cooldowns = mapOf<Command, Map<UUID, Int>>()

    fun add(command: Command, player: Player, cooldown: Int) {
        cooldowns.plus(Pair(command, mapOf(Pair(player.uniqueId, cooldown + System.currentTimeMillis()))))
    }

    fun get(command: Command, player: Player): Int {
        return cooldowns[command]!![player.uniqueId]!!
    }

    fun can(command: Command, player: Player): Boolean {
        return cooldowns.containsKey(command) &&
                cooldowns[command]!!.containsKey(player.uniqueId) &&
                cooldowns[command]!![player.uniqueId]!! < System.currentTimeMillis()
    }
}