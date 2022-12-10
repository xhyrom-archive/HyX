package me.xhyrom.hyx.commands.virtual;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.entity.Player;

@Command("workbench")
@Alias({"wb"})
@Permission("hyx.command.workbench")
public class Workbench {
    @Default
    public static void workbench(
            Player player
    ) {
        player.openWorkbench(player.getLocation(), true);
    }
}
