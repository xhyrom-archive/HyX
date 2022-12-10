package me.xhyrom.hyx.modules.api

import me.xhyrom.hylib.common.api.HyLibProvider
import me.xhyrom.hylib.common.api.structs.Config
import me.xhyrom.hyx.HyX
import java.io.File

abstract class Module(name: String) {
    val config: Config = HyLibProvider.get().getConfigManager().register(
        File(HyX.getInstance()?.dataFolder,"modules/$name.yml").path,
        HyX.getInstanceUnsafe().getResource("modules/$name.yml")!!
    )

    init {
        HyX.getInstanceUnsafe().modules().modules[name] = this
    }

    abstract fun onConfigReload()

    open fun onEnable() {}
}