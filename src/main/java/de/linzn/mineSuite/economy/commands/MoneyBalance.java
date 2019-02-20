package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import de.linzn.mineSuite.economy.utils.EconomyLanguageDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyBalance implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneyBalance(EconomyPlugin economyPlugin, String permission) {
        this.economyPlugin = economyPlugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(Command cmd, CommandSender sender, String[] args) {
        String playerName;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(permission)) {
                player.sendMessage(LanguageDB.NO_PERMISSIONS);
                return true;
            }
            if (args.length <= 1) {
                playerName = player.getName();
            } else {
                playerName = args[1];
            }
        } else {
            if (args.length <= 1) {
                sender.sendMessage(LanguageDB.NO_CONSOLE);
                return true;
            } else {
                playerName = args[1];
            }
        }
        double value = EconomyManager.getBalance(playerName);
        if (args.length <= 1) {
            sender.sendMessage(EconomyLanguageDB.SHOW_BALANCE_SELF.replace("{value}", EconomyManager.formatValue(value)));
        } else {
            sender.sendMessage(EconomyLanguageDB.SHOW_BALANCE_OTHER.replace("{value}", EconomyManager.formatValue(value)).replace("{player}", playerName));
        }
        return true;
    }
}
