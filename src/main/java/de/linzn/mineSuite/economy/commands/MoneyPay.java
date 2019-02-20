package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import de.linzn.mineSuite.economy.utils.EconomyLanguageDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyPay implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneyPay(EconomyPlugin economyPlugin, String permission) {
        this.economyPlugin = economyPlugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(Command cmd, CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LanguageDB.NO_CONSOLE);
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(permission)) {
            player.sendMessage(LanguageDB.NO_PERMISSIONS);
            return true;
        }

        if (args.length < 3) {
            player.sendMessage(LanguageDB.COMMAND_USAGE.replace("{command}", "/money pay <player> <amount>"));
        }

        String playerName = args[1];
        double value = Double.parseDouble(args[2]);

        if (playerName.equalsIgnoreCase(player.getName())) {
            player.sendMessage(EconomyLanguageDB.ERROR_PAY_TRANSACTION.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
            return true;
        }
        if (EconomyManager.transactionBetweenProfiles(player.getName(), playerName, value)) {
            player.sendMessage(EconomyLanguageDB.SUCCESS_PAY_TRANSACTION.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
        } else {
            player.sendMessage(EconomyLanguageDB.ERROR_PAY_TRANSACTION.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
        }
        return true;
    }
}
