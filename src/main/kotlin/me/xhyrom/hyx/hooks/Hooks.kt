package me.xhyrom.hyx.hooks

import me.xhyrom.hyx.hooks.impl.Vault
import org.bukkit.Bukkit

class Hooks {
    var vault: Vault? = null

    init {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            vault = Vault()
        }
    }
}