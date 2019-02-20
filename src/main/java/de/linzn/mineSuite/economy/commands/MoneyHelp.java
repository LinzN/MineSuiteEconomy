package de.linzn.mineSuite.economy.commands;

import de.linzn.mineSuite.core.utils.LanguageDB;
import de.linzn.mineSuite.economy.EconomyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyHelp implements ICommand {

    private EconomyPlugin economyPlugin;
    private String permission;

    public MoneyHelp(EconomyPlugin economyPlugin, String permission) {
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
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("2")) {
                sender.sendMessage(("§6§lAllgemeine Hilfe: "));
                sender.sendMessage(" §2Kontostand anzeigen: §e/money balance <Spieler>");
                sender.sendMessage(" §2Topliste anzeigen: §e/money top <Seite>");
                sender.sendMessage(" §2Hilfeseite: §e/money help <Seite>");
                return true;
            } else if (args[1].equalsIgnoreCase("3")) {
                sender.sendMessage(("§6§lAdmin Hilfe: "));
                sender.sendMessage(" §2Kontostand setzen: §e/money set <Spieler> <Menge>");
                return true;
            }
        }

        sender.sendMessage("§e§n§6§l-============[§2§lMineSuiteEconomy§r§6§l]============-");
        sender.sendMessage("§2 Dein Kontostand anzeigen: §e/money");
        sender.sendMessage("§2 Transaktion durchführen: §e/money pay <Spieler> <Menge>");
        sender.sendMessage("§6§lÜbersicht der Economy Hilfebereiche:");
        sender.sendMessage(" §2Allgemeine Hilfe §a/money help 2");
        sender.sendMessage(" §2Admin Hilfe §a/money help 3");
        return true;
    }
}
