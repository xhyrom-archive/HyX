package me.xhyrom.hyx.modules.impl

import me.xhyrom.hyx.HyX
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import java.util.*

class Vanish : me.xhyrom.hyx.modules.api.Module("vanish") {
    val vanished = mutableListOf<UUID>()
    private val vanishedBossbar = BossBar.bossBar(
        MiniMessage.miniMessage().deserialize(
            HyX.getInstanceUnsafe().lang().getString("bossbar.vanished.message").get(),
        ),
        1F,
        BossBar.Color.valueOf(config.getString("bossbar.color").get().uppercase()),
        BossBar.Overlay.valueOf(config.getString("bossbar.overlay").get().uppercase()),
    )

    override fun onConfigReload() {
        changeColor(config.getString("bossbar.color").get())
        changeOverlay(config.getString("bossbar.overlay").get())
        changeName(HyX.getInstanceUnsafe().lang().getString("bossbar.vanished.message").get())
    }

    fun changeName(name: String) {
        vanishedBossbar.name(MiniMessage.miniMessage().deserialize(name))
    }

    fun changeColor(color: BossBar.Color) {
        vanishedBossbar.color(color)
    }

    fun changeColor(color: String) {
        vanishedBossbar.color(BossBar.Color.valueOf(color.uppercase()))
    }

    fun changeOverlay(overlay: BossBar.Overlay) {
        vanishedBossbar.overlay(overlay)
    }

    fun changeOverlay(overlay: String) {
        vanishedBossbar.overlay(BossBar.Overlay.valueOf(overlay.uppercase()))
    }

    fun isVanished(player: Player): Boolean {
        return vanished.contains(player.uniqueId)
    }

    fun setVanished(player: Player, vanished: Boolean) {
        if (vanished) {
            this.vanished.add(player.uniqueId)
            player.showBossBar(vanishedBossbar)

            hidePlayer(player)
        } else {
            this.vanished.remove(player.uniqueId)
            player.hideBossBar(vanishedBossbar)

            showPlayer(player)
        }
    }

    fun hidePlayer(player: Player) {
        for (onlinePlayer in player.server.onlinePlayers) {
            if (!onlinePlayer.hasPermission("hyx.command.vanish.see")) {
                onlinePlayer.hidePlayer(HyX.getInstanceUnsafe(), player)
            }
        }
    }

    fun showPlayer(player: Player) {
        for (onlinePlayer in player.server.onlinePlayers) {
            onlinePlayer.showPlayer(HyX.getInstanceUnsafe(), player)
        }
    }
}