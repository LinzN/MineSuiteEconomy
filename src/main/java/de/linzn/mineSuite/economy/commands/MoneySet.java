package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MoneySet implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneySet(EconomyPlugin economyPlugin, String permission) {
        this.economyPlugin = economyPlugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(Command cmd, CommandSender sender, String[] args) {
        if (args.length < 3) {
            sender.sendMessage("Wrong usage!");
        }

        String playerName = args[1];
        double value = Double.parseDouble(args[2]);
        EconomyManager.setProfileBalance(playerName, value);
        sender.sendMessage("Balance set " + playerName + " to: " + EconomyManager.formatValue(value));

        return true;
    }
}
