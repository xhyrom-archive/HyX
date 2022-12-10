package me.xhyrom.hyx.hooks.impl

import me.xhyrom.hyx.HyX
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission

class Vault {
    private var economy: Economy? = null
    private var permissions: Permission? = null
    private var chat: Chat? = null

    init {
        economy = HyX.getInstanceUnsafe().server.servicesManager.getRegistration(Economy::class.java)?.provider
        permissions = HyX.getInstanceUnsafe().server.servicesManager.getRegistration(Permission::class.java)?.provider
        chat = HyX.getInstanceUnsafe().server.servicesManager.getRegistration(Chat::class.java)?.provider
    }

    fun getEconomy(): Economy? {
        return economy
    }

    fun getEconomyUnsafe(): Economy {
        return economy!!
    }

    fun getPermissions(): Permission? {
        return permissions
    }

    fun getPermissionsUnsafe(): Permission {
        return permissions!!
    }

    fun getChat(): Chat? {
        return chat
    }

    fun getChatUnsafe(): Chat {
        return chat!!
    }
}