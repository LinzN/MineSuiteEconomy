package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import de.linzn.mineSuite.economy.utils.EconomyLanguageDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneySet implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneySet(EconomyPlugin economyPlugin, String permission) {
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

        if (args.length < 3) {
            sender.sendMessage(LanguageDB.COMMAND_USAGE.replace("{command}", "/money set <player> <amount>"));
        }

        String playerName = args[1];
        double value = Double.parseDouble(args[2]);

        if (EconomyManager.setProfileBalance(playerName, value)) {
            sender.sendMessage(EconomyLanguageDB.SUCCESS_SET_PROFILE.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
        } else {
            sender.sendMessage(EconomyLanguageDB.ERROR_SET_PROFILE.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
        }

        return true;
    }
}
