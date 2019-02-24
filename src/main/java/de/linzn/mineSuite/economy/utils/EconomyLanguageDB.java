package de.linzn.mineSuite.economy.utils;

import org.bukkit.ChatColor;

public class EconomyLanguageDB {
    public static String SUCCESS_PAY_TRANSACTION = ChatColor.GREEN + "Transaktion von " + ChatColor.YELLOW + "{value} " + ChatColor.GREEN + "an " + ChatColor.YELLOW + "{player} " + ChatColor.GREEN + "erfolgreich durchgeführt!";
    public static String ERROR_PAY_TRANSACTION = ChatColor.RED + "Transaktion von " + ChatColor.GOLD + "{value} " + ChatColor.RED + "an " + ChatColor.GOLD + "{player} " + ChatColor.RED + "fehlgeschlagen!";

    public static String SUCCESS_SET_PROFILE = ChatColor.GREEN + "Setzen des Kontostandes für " + ChatColor.YELLOW + "{player}" + ChatColor.GREEN + " auf " + ChatColor.YELLOW + "{value}" + ChatColor.GREEN + " erfolgreich!";
    public static String ERROR_SET_PROFILE = ChatColor.RED + "Setzen des Kontostandes für " + ChatColor.GOLD + "{player} " + ChatColor.RED + "auf " + ChatColor.GOLD + "{value} " + ChatColor.RED + "fehlgeschlagen!";

    public static String SHOW_BALANCE_SELF = ChatColor.GREEN + "Dein Kontostand: " + ChatColor.YELLOW + "{value}";
    public static String SHOW_BALANCE_OTHER = ChatColor.GREEN + "{player}`s Kontostand: " + ChatColor.YELLOW + "{value}";

    public static String ERROR_NO_PROFILE = ChatColor.RED + "Dieses Profil gibt es nicht!";
}