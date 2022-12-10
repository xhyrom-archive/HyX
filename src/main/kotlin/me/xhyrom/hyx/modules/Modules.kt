package me.xhyrom.hyx.modules

import me.xhyrom.hyx.modules.impl.economy.Economy
import me.xhyrom.hyx.modules.impl.Vanish

class Modules {
    val modules = HashMap<String, me.xhyrom.hyx.modules.api.Module>()

    var vanish: Vanish? = null
    var economy: Economy? = null

    fun init() {
        vanish = Vanish()

        economy = Economy()
        economy!!.onEnable()
    }
}