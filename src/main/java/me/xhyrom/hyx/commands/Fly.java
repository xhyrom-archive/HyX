package me.xhyrom.hyx.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

@Command("fly")
@Permission("hyx.command.fly")
public class Fly {
    @Default
    public static void fly(
            Player player
    ) {
        player.setAllowFlight(!player.getAllowFlight());

        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstanceUnsafe().lang().getString(
                        "commands.fly." +
                                (player.getAllowFlight()
                                        ? "enable.message"
                                        : "disable.message"
                                )
                ).get()
        ));
    }
}
