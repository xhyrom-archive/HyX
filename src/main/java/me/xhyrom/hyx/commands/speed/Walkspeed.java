package me.xhyrom.hyx.commands.speed;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

@Command("walkspeed")
@Alias({"ws"})
@Permission("hyx.command.speed.walk")
public class Walkspeed {
    @Default
    public static void walkspeed(
            Player player,
            @AIntegerArgument(min = 0, max = 10) int speed
    ) {
        player.setWalkSpeed(speed / 10f);
        player.sendMessage(MiniMessage.miniMessage().deserialize(
                HyX.Companion.getInstanceUnsafe().lang().getString("commands.speed.walk.success.message").get(),
                Placeholder.unparsed("speed", String.valueOf(speed))
        ));
    }
}
