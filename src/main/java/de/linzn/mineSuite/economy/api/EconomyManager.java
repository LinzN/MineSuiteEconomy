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

import de.linzn.mineSuite.core.database.CacheManager;
import de.linzn.mineSuite.economy.mysql.EconomyQuery;
import de.linzn.mineSuite.economy.utils.EconomyType;
import de.linzn.openJL.math.FloatingPoint;
import de.linzn.openJL.pairs.Pair;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EconomyManager {
    private static HashMap<String, String> settings = new HashMap<>();

    public static boolean createProfile(String playerName) {

        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return createProfile(guildUUID, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return createProfile(playerUUID, EconomyType.PLAYER);
    }

    public static boolean createProfile(UUID entityUUID, EconomyType economyType) {
        double defaultValue = Double.parseDouble(EconomyManager.getSetting("currency.defaultValue"));
        return EconomyQuery.createProfile(entityUUID, economyType, defaultValue);
    }

    public static boolean deleteProfile(String playerName) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return deleteProfile(guildUUID, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return deleteProfile(playerUUID, EconomyType.PLAYER);
    }

    public static boolean deleteProfile(UUID entityUUID, EconomyType economyType) {
        return EconomyQuery.deleteProfile(entityUUID, economyType);
    }

    public static boolean hasProfile(String playerName) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return hasProfile(guildUUID, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return hasProfile(playerUUID, EconomyType.PLAYER);
    }

    public static boolean hasProfile(UUID entityUUID, EconomyType economyType) {
        return EconomyQuery.hasProfile(entityUUID, economyType);
    }

    public static double getBalance(String playerName) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return getBalance(guildUUID, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return 0.0D;
        }
        return getBalance(playerUUID, EconomyType.PLAYER);
    }

    public static double getBalance(UUID entityUUID, EconomyType economyType) {
        double balance = EconomyQuery.getProfileBalance(entityUUID, economyType);
        if (balance == -1.0) {
            balance = Double.parseDouble(EconomyManager.getSetting("currency.defaultValue"));
        }
        return balance;
    }

    public static EconomyResponse withdrawProfile(String playerName, double value) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return withdrawProfile(guildUUID, value, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return new EconomyResponse(value, 0.0, EconomyResponse.ResponseType.FAILURE, "PlayerUUID is null");
        }
        return withdrawProfile(playerUUID, value, EconomyType.PLAYER);
    }

    public static EconomyResponse withdrawProfile(UUID entityUUID, double value, EconomyType economyType) {
        value = round(value);
        double currentBalance = getBalance(entityUUID, economyType);
        if (currentBalance == -1.0) {
            currentBalance = Double.parseDouble(EconomyManager.getSetting("currency.defaultValue"));
        }
        if (value < 0.01) {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.FAILURE, "Error on transaction EC 1 - negative value");
        }
        double withdrawBalance = currentBalance - Math.abs(value);
        boolean success = EconomyQuery.updateProfile(entityUUID, withdrawBalance, economyType);
        if (success) {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.FAILURE, "Error on transaction EC 2 - database error");
        }
    }

    public static EconomyResponse depositProfile(String playerName, double value) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return depositProfile(guildUUID, value, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return new EconomyResponse(value, 0.0, EconomyResponse.ResponseType.FAILURE, "PlayerUUID is null");
        }
        return depositProfile(playerUUID, value, EconomyType.PLAYER);
    }

    public static EconomyResponse depositProfile(UUID entityUUID, double value, EconomyType economyType) {
        value = round(value);
        double currentBalance = getBalance(entityUUID, economyType);
        if (currentBalance == -1.0) {
            currentBalance = Double.parseDouble(EconomyManager.getSetting("currency.defaultValue"));
        }

        if (value < 0.01) {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.FAILURE, "Error on transaction EC 1 - negative value");
        }
        double depositBalance = currentBalance + Math.abs(value);
        boolean success = EconomyQuery.updateProfile(entityUUID, depositBalance, economyType);
        if (success) {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.SUCCESS, null);
        } else {
            return new EconomyResponse(value, currentBalance, EconomyResponse.ResponseType.FAILURE, "Error on transaction EC 2 - database error");
        }
    }

    public static boolean setProfileBalance(String playerName, double value) {
        if (isGuildAccount(playerName)) {
            UUID guildUUID = getGuildUUID(playerName);
            return setProfileBalance(guildUUID, value, EconomyType.GUILD);
        }

        UUID playerUUID = CacheManager.getPlayerUUID(playerName);
        if (playerUUID == null) {
            return false;
        }
        return setProfileBalance(playerUUID, value, EconomyType.PLAYER);
    }

    public static boolean setProfileBalance(UUID entityUUID, double value, EconomyType economyType) {
        value = round(value);
        if (value < 0) {
            return false;
        }
        return EconomyQuery.updateProfile(entityUUID, value, economyType);
    }

    public static boolean transactionBetweenProfiles(String fromName, String toName, double value) {
        if (fromName.equalsIgnoreCase(toName)) {
            return false;
        }

        UUID fromUUID;
        EconomyType fromType;
        if (isGuildAccount(fromName)) {
            fromUUID = getGuildUUID(fromName);
            fromType = EconomyType.GUILD;
        } else {
            fromUUID = CacheManager.getPlayerUUID(fromName);
            fromType = EconomyType.PLAYER;
        }

        if (fromUUID == null) {
            return false;
        }

        UUID toUUID;
        EconomyType toType;
        if (isGuildAccount(toName)) {
            toUUID = getGuildUUID(toName);
            toType = EconomyType.GUILD;
        } else {
            toUUID = CacheManager.getPlayerUUID(toName);
            toType = EconomyType.PLAYER;
        }

        if (toUUID == null) {
            return false;
        }

        return transactionBetweenProfiles(fromUUID, fromType, toUUID, toType, value);
    }

    public static boolean transactionBetweenProfiles(UUID fromEntityUUID, EconomyType fromType, UUID toEntityUUID, EconomyType toType, double value) {
        if (fromEntityUUID.equals(toEntityUUID)) {
            return false;
        }
        value = round(value);

        double fromBalance = getBalance(fromEntityUUID, fromType);
        double toBalance = getBalance(toEntityUUID, toType);
        if (value < 0.01) {
            return false;
        }
        fromBalance -= value;
        toBalance += value;

        if (fromBalance < 0) {
            return false;
        }
        if (toBalance < 0) {
            return false;
        }

        EconomyQuery.updateProfile(fromEntityUUID, fromBalance, fromType);
        EconomyQuery.updateProfile(toEntityUUID, toBalance, toType);
        return true;
    }

    public static List<Pair<UUID, Double>> getTopMap(EconomyType type, int page, int limit) {
        int offsetFull = 0;
        for (int i = 1; i < page; i++) {
            offsetFull += limit;
        }
        return EconomyQuery.getProfiles(type, limit, offsetFull);
    }

    public static String formatValue(double value) {
        if (value > 0.99D && value < 1.01) {
            return "" + round(value) + " " + EconomyManager.getSetting("currency.name.singular");
        }
        return "" + round(value) + " " + EconomyManager.getSetting("currency.name.plural");
    }

    public static boolean isGuildAccount(String name) {
        return name.startsWith("guild_");
    }

    public static UUID getGuildUUID(String name) {
        return UUID.fromString(name.split("guild_")[1]);
    }

    public static void reloadSettings() {
        settings.clear();
        EconomyQuery.loadSettings();
    }

    public static String getSetting(String setting) {
        return settings.get(setting);
    }

    public static void addSetting(String setting, String value) {
        settings.put(setting, value);
    }


    private static double round(double value) {
        return FloatingPoint.round(value, 2);
    }
}
