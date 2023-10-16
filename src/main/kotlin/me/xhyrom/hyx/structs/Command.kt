package me.xhyrom.hyx.structs

import me.xhyrom.hylib.libs.boostedyaml.YamlDocument
import me.xhyrom.hylib.libs.commandapi.CommandAPICommand
import me.xhyrom.hyx.HyX
import me.xhyrom.hyx.middlewares.CooldownMiddleware
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

open class Command(val name: String) {
    val command = CommandAPICommand(name)
    private var cooldowns = mapOf<String, Int>()

    init {
        val section = (HyX.getInstanceUnsafe().commandsConfig().getRaw() as YamlDocument).getSection("cooldowns.$name")

        (section.getStringRouteMappedValues(false)).forEach {
            (key, value) -> cooldowns.plus(Pair(key as String, value as Int))
        }
    }

    fun execute(sender: CommandSender, executor: () -> Unit) {
        if (cooldowns.isEmpty() || sender !is Player) {
            executor()
            return
        }

        for (cooldown in cooldowns) {
            if (sender.hasPermission("hyx.command.$name.cooldown.$cooldown")) {
                if (CooldownMiddleware.can(this, sender)) {
                    executor()
                    CooldownMiddleware.add(this, sender, cooldown.value)
                    return
                }

                sender.sendMessage(
                    MiniMessage.miniMessage().deserialize(
                        HyX.getInstanceUnsafe().lang().getString(
                            "commands.$name.cooldown.message"
                        ).get(),
                        Placeholder.parsed("cooldown", CooldownMiddleware.get(this, sender).toString())
                    )
                )

                break
            }
        }
    }

    fun register() {
        command.register()
    }
}