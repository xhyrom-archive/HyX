package me.xhyrom.hyx.commands.gamemodes;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command("gms")
@Permission("hyx.command.gamemode.survival")
public class Gms {
    @Default
    public static void gms(Player player) {
        player.setGameMode(GameMode.SURVIVAL);

        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstance().lang().getString("commands.gamemode.success.message").get(),
                Placeholder.component(
                        "type",
                        Component.text(
                                HyX.Companion.getInstance().lang().getString("commands.gamemode.types.survival").get()
                        )
                )
        ));
    }
}
