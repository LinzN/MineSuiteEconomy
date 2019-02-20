package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import de.linzn.mineSuite.economy.utils.EconomyType;
import de.linzn.openJL.pairs.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class MoneyTop implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneyTop(EconomyPlugin economyPlugin, String permission) {
        this.economyPlugin = economyPlugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(Command cmd, CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(permission)) {
                player.sendMessage(LanguageDB.NO_PERMISSIONS);
                return true;
            }
        }
        int page;
        if (args.length >= 2) {
            page = Integer.parseInt(args[1]);
            if (page < 1) {
                page = 1;
            }
        } else {
            page = 1;
        }
        List<Pair<UUID, Double>> profilesMap = EconomyManager.getTopMap(EconomyType.PLAYER, page, 5);
        for (Pair<UUID, Double> pair : profilesMap) {
            sender.sendMessage("UUID: " + pair.getKey() + " Value: " + pair.getValue());
        }

        return true;
    }
}
