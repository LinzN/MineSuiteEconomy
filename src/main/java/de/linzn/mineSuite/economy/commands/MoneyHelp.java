package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.economy.EconomyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MoneyHelp implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneyHelp(EconomyPlugin economyPlugin, String permission) {
        this.economyPlugin = economyPlugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(Command cmd, CommandSender sender, String[] args) {
        return false;
    }
}
