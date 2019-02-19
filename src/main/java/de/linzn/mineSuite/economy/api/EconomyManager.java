/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.mineSuite.economy.api;

import de.linzn.mineSuite.core.database.BukkitQuery;
import de.linzn.mineSuite.economy.utils.EconomyType;
import de.linzn.mineSuite.economy.mysql.EconomyQuery;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.HashMap;
import java.util.UUID;

public class EconomyManager {
    private static HashMap<String, String> settings = new HashMap<>();


    public static boolean createProfile(String playerName) {
        UUID playerUUID = BukkitQuery.getUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return createProfile(playerUUID, EconomyType.PLAYER);
    }

    public static boolean createProfile(UUID playerUUID, EconomyType economyType) {
        double defaultValue = Double.parseDouble(EconomyManager.getSetting("currency.defaultValue"));
        return false;
    }

    public static boolean hasProfile(String playerName) {
        UUID playerUUID = BukkitQuery.getUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return hasProfile(playerUUID, EconomyType.PLAYER);
    }

    public static boolean hasProfile(UUID playerUUID, EconomyType economyType) {
        return EconomyQuery.hasProfile(playerUUID);
    }

    public static double getBalance(String playerName) {
        UUID playerUUID = BukkitQuery.getUUID(playerName);
        if (playerUUID == null) {
            return 0.0D;
        }
        return getBalance(playerUUID, EconomyType.PLAYER);
    }

    public static double getBalance(UUID playerUUID, EconomyType economyType) {
        return EconomyQuery.getProfileBalance(playerUUID);
    }

    public static EconomyResponse withdrawProfile(String playerName, double value) {
        UUID playerUUID = BukkitQuery.getUUID(playerName);
        if (playerUUID == null) {
            return new EconomyResponse(value, 0.0, EconomyResponse.ResponseType.FAILURE, "PlayerUUID is null");
        }
        return withdrawProfile(playerUUID, value, EconomyType.PLAYER);
    }

    public static EconomyResponse withdrawProfile(UUID playerUUID, double value, EconomyType economyType) {
        return null;
    }

    public static EconomyResponse depositeProfile(String playerName, double value) {
        UUID playerUUID = BukkitQuery.getUUID(playerName);
        if (playerUUID == null) {
            return new EconomyResponse(value, 0.0, EconomyResponse.ResponseType.FAILURE, "PlayerUUID is null");
        }
        return depositeProfile(playerUUID, value, EconomyType.PLAYER);
    }

    public static EconomyResponse depositeProfile(UUID playerUUID, double value, EconomyType economyType) {
        return null;
    }

    public static String formatValue(double value) {
        if (value > 0.99D && value < 1.01) {
            return "" + value + EconomyManager.getSetting("currency.name.singular");
        }
        return "" + value + EconomyManager.getSetting("currency.name.plural");
    }


    public static String getSetting(String setting) {
        return settings.get(setting);
    }

    public static void addSetting(String setting, String value) {
        settings.put(setting, value);
    }
}
