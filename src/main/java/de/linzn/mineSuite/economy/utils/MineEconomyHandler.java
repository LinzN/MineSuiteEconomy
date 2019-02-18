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

package de.linzn.mineSuite.economy.utils;


import de.linzn.mineSuite.economy.EconomyPlugin;
import de.linzn.mineSuite.economy.api.EconomyManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class MineEconomyHandler implements Economy {

    @Override
    public boolean isEnabled() {
        return EconomyPlugin.inst().isEnabled();
    }

    @Override
    public String getName() {
        return "MineSuiteEconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        return EconomyManager.formatValue(v);
    }

    @Override
    public String currencyNamePlural() {
        return EconomyManager.getSetting("currency.name.plural");
    }

    @Override
    public String currencyNameSingular() {
        return EconomyManager.getSetting("currency.name.singular");
    }

    @Override
    public boolean hasAccount(String s) {
        return EconomyManager.hasProfile(s);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return EconomyManager.hasProfile(offlinePlayer.getUniqueId());
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        /* no multiworld support */
        return EconomyManager.hasProfile(s);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        /* no multiworld support */
        return EconomyManager.hasProfile(offlinePlayer.getUniqueId());
    }

    @Override
    public double getBalance(String s) {
        return EconomyManager.getBalance(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return EconomyManager.getBalance(offlinePlayer.getUniqueId());
    }

    @Override
    public double getBalance(String s, String s1) {
        /* no multiworld support */
        return EconomyManager.getBalance(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        /* no multiworld support */
        return EconomyManager.getBalance(offlinePlayer.getUniqueId());
    }

    @Override
    public boolean has(String s, double v) {
        return getBalance(s) >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return getBalance(offlinePlayer) >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        /* no multiworld support */
        return getBalance(s) >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        /* no multiworld support */
        return getBalance(offlinePlayer) >= v;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return EconomyManager.createProfile(s);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return EconomyManager.createProfile(offlinePlayer.getUniqueId());
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        /* no multiworld support */
        return EconomyManager.createProfile(s);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        /* no multiworld support */
        return EconomyManager.createProfile(offlinePlayer.getUniqueId());
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return EconomyManager.withdrawProfile(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        return EconomyManager.withdrawProfile(offlinePlayer.getUniqueId(), v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        /* no multiworld support */
        return EconomyManager.withdrawProfile(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        /* no multiworld support */
        return EconomyManager.withdrawProfile(offlinePlayer.getUniqueId(), v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return EconomyManager.depositeProfile(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return EconomyManager.depositeProfile(offlinePlayer.getUniqueId(), v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        /* no multiworld support */
        return EconomyManager.depositeProfile(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        /* no multiworld support */
        return EconomyManager.depositeProfile(offlinePlayer.getUniqueId(), v);
    }


    /* Bank stuff */
    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }
}
