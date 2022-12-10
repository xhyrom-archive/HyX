package me.xhyrom.hyx.commands.virtual;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.entity.Player;

@Command("anvil")
@Permission("hyx.command.anvil")
public class Anvil {
    @Default
    public static void anvil(
            Player player
    ) {
        player.openAnvil(player.getLocation(), true);
    }
}
