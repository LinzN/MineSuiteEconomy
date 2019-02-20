package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        return true;
    }
}
