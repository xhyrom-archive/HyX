package me.xhyrom.hyx

import me.xhyrom.hylib.bukkit.api.structs.BukkitCommand
import me.xhyrom.hylib.common.api.HyLibProvider
import me.xhyrom.hylib.common.api.structs.Command
import me.xhyrom.hylib.common.api.structs.Config
import me.xhyrom.hylib.common.api.structs.Language
import me.xhyrom.hylib.libs.commandapi.CommandAPI
import me.xhyrom.hylib.libs.commandapi.arguments.ArgumentSuggestions
import me.xhyrom.hylib.libs.commandapi.arguments.StringArgument
import me.xhyrom.hylib.libs.commandapi.executors.CommandExecutor
import me.xhyrom.hyx.commands.gamemodes.*
import me.xhyrom.hyx.commands.virtual.*
import me.xhyrom.hyx.commands.*
import me.xhyrom.hyx.listeners.PlayerListener
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import kotlin.reflect.KClass

class HyX : JavaPlugin() {
    companion object {
        private var instance: HyX? = null

        fun getInstance(): HyX {
            return instance!!
        }
    }

    private var xConfig: Config? = null
    private var commandsConfig: Config? = null
    private var language: Language? = null

    override fun onEnable() {
        instance = this

        xConfig = HyLibProvider.get().getConfigManager().register(File(this.dataFolder, "config.yml").path, this.getResource("config.yml")!!)
        commandsConfig = HyLibProvider.get().getConfigManager().register(File(this.dataFolder, "commands.yml").path, this.getResource("commands.yml")!!)
        language = HyLibProvider.get().getLanguageManager().register(this.dataFolder.path) { path ->
            this.getResource(path)!!
        }

        server.pluginManager.registerEvents(PlayerListener(), this)

        val mode = commandsConfig().getString("mode").get()

        registerCommand(mode, "gamemode", Gamemode::class)
        registerCommand(mode, "gma", Gma::class)
        registerCommand(mode, "gmc", Gmc::class)
        registerCommand(mode, "gms", Gms::class)
        registerCommand(mode, "gmsp", Gmsp::class)

        // Virtual commands
        registerCommand(mode, "anvil", Anvil::class)
        registerCommand(mode, "workbench", Workbench::class)
        registerCommand(mode, "enderchest", EnderChest::class)

        registerCommand(mode, "vanish", VanishCommand::class)

        createCommand()
    }

    private fun registerCommand(mode: String, commandName: String, commandClass: KClass<out Any>) {
        if (
            (
                    mode == "whitelist" &&
                    commandsConfig().getStringList("commands").get().contains(commandName)
            ) ||
            !commandsConfig().getStringList("commands").get().contains(commandName)
        ) CommandAPI.registerCommand(commandClass.java)
    }

    private fun createCommand() {
        HyLibProvider.get().getCommandManager().register(
            (HyLibProvider.get().getCommandManager().create("x") as BukkitCommand)
                .withFullDescription("hyx plugin management")
                .withSubcommand(
                    BukkitCommand("reload")
                        .withFullDescription("reload config")
                        .withPermission("hyx.command.reload")
                        .withArguments(
                            StringArgument("type").includeSuggestions(
                                ArgumentSuggestions.strings(
                                    "config",
                                    "lang"
                                )
                            )
                        )
                        .executes(
                            CommandExecutor { sender: CommandSender, args: Array<Any?> ->
                                run {
                                    when (args[0] as String) {
                                        "config" -> {
                                            if (!xConfig().reload()) {
                                                sender.sendMessage(
                                                    MiniMessage.miniMessage().deserialize(
                                                        lang().getString("commands.x.reload.fail").get(),
                                                        Placeholder.component("type", Component.text("X"))
                                                    )
                                                )

                                                return@CommandExecutor
                                            }

                                            sender.sendMessage(
                                                MiniMessage.miniMessage().deserialize(
                                                    lang().getString("commands.x.reload.success").get(),
                                                    Placeholder.component("type", Component.text("X"))
                                                )
                                            )
                                        }
                                        "lang" -> {
                                            if (!lang().reload()) {
                                                sender.sendMessage(
                                                    MiniMessage.miniMessage().deserialize(
                                                        lang().getString("commands.x.reload.fail").get(),
                                                        Placeholder.component("type", Component.text("Language"))
                                                    )
                                                )

                                                return@CommandExecutor
                                            }

                                            sender.sendMessage(
                                                MiniMessage.miniMessage().deserialize(
                                                    lang().getString("commands.x.reload.success").get(),
                                                    Placeholder.component("type", Component.text("Language"))
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        )
                ) as Command
        )
    }

    fun lang(): Config {
        return language!!.getLang(xConfig().getString("language").get())
    }

    fun xConfig(): Config {
        return xConfig!!
    }

    fun commandsConfig(): Config {
        return commandsConfig!!
    }
}