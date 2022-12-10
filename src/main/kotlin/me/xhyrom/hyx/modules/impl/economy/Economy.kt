package me.xhyrom.hyx.modules.impl.economy

import me.xhyrom.hyx.HyX
import me.xhyrom.hyx.commands.economy.Balance

class Economy : me.xhyrom.hyx.modules.api.Module("economy") {
    private val enabled: Boolean = config.getBoolean("enabled").orElse(true)

    override fun onEnable() {
        if (!enabled) return

        if (HyX.getInstanceUnsafe().hooks().vault == null) {
            HyX.getInstanceUnsafe().logger.warning("Vault not found, disabling economy module.")
            HyX.getInstanceUnsafe().modules().modules.remove("economy")

            return
        }

        val mode = HyX.getInstanceUnsafe().commandsConfig().getString("mode").get()
        HyX.getInstanceUnsafe().registerCommand(mode, "balance", Balance::class)
    }

    override fun onConfigReload() {

    }
}