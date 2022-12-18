package me.xhyrom.hyx.commands.virtual;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.entity.Player;

@Command("enderchest")
@Alias({"ec"})
@Permission("hyx.command.enderchest")
public class EnderChest {
    @Default
    public static void enderchest(
            Player player
    ) {
        player.openInventory(player.getEnderChest());
    }

    @Default
    @Permission("hyx.command.enderchest.others")
    public static void enderchest(
            Player player,
            @APlayerArgument Player target
    ) {
        player.openInventory(target.getEnderChest());
    }
}
