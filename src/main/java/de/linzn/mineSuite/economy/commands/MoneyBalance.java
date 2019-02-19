package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
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
        String playerName = null;
        if (args.length <= 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("No console account");
                return false;
            }
            playerName = sender.getName();
        }

        if (args.length > 1) {
            playerName = args[1];
        }

        double value = EconomyManager.getBalance(playerName);
        sender.sendMessage("Balance: " + EconomyManager.formatValue(value));
        return false;
    }
}
