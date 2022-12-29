package me.xhyrom.hyx.commands.virtual;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.entity.Player;

@Command("invsee")
@Permission("hyx.command.invsee")
public class Invsee {
    @Default
    public static void invsee(
            Player player
    ) {
        player.openInventory(player.getInventory());
    }

    @Default
    @Permission("hyx.command.invsee.others")
    public static void invsee(
            Player player,
            @APlayerArgument Player target
    ) {
        player.openInventory(target.getInventory());
    }
}
