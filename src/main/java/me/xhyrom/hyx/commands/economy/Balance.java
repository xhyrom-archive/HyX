package me.xhyrom.hyx.commands.economy;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.entity.Player;

@Command("balance")
public class Balance {
    @Default
    public static void balance(Player player) {
        player.sendMessage("random test lol");
    }

    @Default
    @Permission("hyx.command.balance.others")
    public static void balance(
            Player player,
            @APlayerArgument Player target
    ) {
        player.sendMessage("random test lol");
    }
}
