package me.xhyrom.hyx.commands.gamemodes;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command("gmc")
@Permission("hyx.command.gamemode.creative")
public class Gmc {
    @Default
    public static void gmc(Player player) {
        player.setGameMode(GameMode.CREATIVE);

        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstance().lang().getString("commands.gamemode.success.message").get(),
                Placeholder.component(
                        "type",
                        Component.text(
                                HyX.Companion.getInstance().lang().getString("commands.gamemode.types.creative").get()
                        )
                )
        ));
    }

    @Default
    @Permission("hyx.command.gamemode.creative.others")
    public static void gmc(Player player, @APlayerArgument Player target) {
        target.setGameMode(GameMode.CREATIVE);

        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstance().lang().getString("commands.gamemode.success.other-message").get(),
                Placeholder.component(
                        "type",
                        Component.text(
                                HyX.Companion.getInstance().lang().getString("commands.gamemode.types.creative").get()
                        )
                ),
                Placeholder.component("player", target.name()),
                Placeholder.component("player_displayname", target.displayName())
        ));
    }
}
