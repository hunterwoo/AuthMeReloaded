package fr.xephi.authme.command.executable.unregister;

import fr.xephi.authme.cache.auth.PlayerCache;
import fr.xephi.authme.command.CommandService;
import fr.xephi.authme.command.PlayerCommand;
import fr.xephi.authme.output.MessageKey;
import fr.xephi.authme.process.Management;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.List;

public class UnregisterCommand extends PlayerCommand {

    @Inject
    private Management management;

    @Inject
    private CommandService commandService;

    @Inject
    private PlayerCache playerCache;

    @Override
    public void runCommand(Player player, List<String> arguments) {
        String playerPass = arguments.get(0);
        final String playerNameLowerCase = player.getName().toLowerCase();

        // Make sure the player is authenticated
        if (!playerCache.isAuthenticated(playerNameLowerCase)) {
            commandService.send(player, MessageKey.NOT_LOGGED_IN);
            return;
        }

        // Unregister the player
        management.performUnregister(player, playerPass, false);
    }
}
