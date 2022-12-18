package me.xhyrom.hyx.commands;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command("vanish")
@Alias({"v"})
@Permission("hyx.command.vanish")
public class VanishCommand {
    @Default
    public static void onDefault(
            Player player
    ) {
        HyX.Companion.getInstanceUnsafe().modules().getVanish().setVanished(
                player,
                !HyX.Companion.getInstanceUnsafe().modules().getVanish().isVanished(player)
        );

        if (
                HyX.Companion.getInstanceUnsafe().modules().getVanish().getConfig().getBoolean(
                        HyX.Companion.getInstanceUnsafe().modules().getVanish().isVanished(player)
                                ? "send-fake-messages.leave"
                                : "send-fake-messages.join"
                ).get()
        ) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("hyx.command.vanish.notify") && HyX.Companion.getInstanceUnsafe().modules().getVanish().getConfig().getBoolean("send-admin-notifications").get()) {
                    onlinePlayer.sendMessage(
                            MiniMessage.miniMessage().deserialize(
                                    HyX.Companion.getInstanceUnsafe().lang().getString(
                                            HyX.Companion.getInstanceUnsafe().modules().getVanish().isVanished(player)
                                                    ? "modules.vanish.notification.disable"
                                                    : "modules.vanish.notification.enable"
                                    ).get(),
                                    Placeholder.component("player", player.name()),
                                    Placeholder.component("player_displayname", player.displayName())
                            )
                    );
                }

                onlinePlayer.sendMessage(
                        MiniMessage.miniMessage().deserialize(
                                HyX.Companion.getInstanceUnsafe().lang().getString(
                                        HyX.Companion.getInstanceUnsafe().modules().getVanish().isVanished(player)
                                                ? "modules.vanish.fake-messages.leave"
                                                : "modules.vanish.fake-messages.join"
                                ).get(),
                                Placeholder.component("player", player.name()),
                                Placeholder.component("player_displayname", player.displayName())
                        )
                );
            }
        }

        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstanceUnsafe().lang().getString(
                        "commands.vanish." +
                                (HyX.Companion.getInstanceUnsafe().modules().getVanish().isVanished(player)
                                        ? "enable.message"
                                        : "disable.message"
                                )
                ).get()
        ));
    }
}
