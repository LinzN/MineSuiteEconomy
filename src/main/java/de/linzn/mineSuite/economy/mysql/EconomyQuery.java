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
 
package de.linzn.mineSuite.economy.mysql;

import de.linzn.mineSuite.core.database.mysql.MySQLConnectionManager;
import de.linzn.mineSuite.economy.api.EconomyManager;
import de.linzn.mineSuite.economy.utils.EconomyType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EconomyQuery {

    public static double getProfileBalance(UUID entityUUID, EconomyType economyType) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        double value = 0.0D;

        try {
            Connection conn = manager.getConnection("mineSuiteEconomy");
            PreparedStatement sql = conn.prepareStatement("SELECT balance FROM module_economy_profiles WHERE uuid = '" + entityUUID.toString() + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                value = result.getDouble("value");
            }
            result.close();
            sql.close();
            manager.release("mineSuiteEconomy", conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

        public static boolean hasProfile(UUID entityUUID, EconomyType economyType) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        boolean hasProfile = false;

        try {
            Connection conn = manager.getConnection("mineSuiteEconomy");
            PreparedStatement sql = conn.prepareStatement("SELECT uuid FROM module_economy_profiles WHERE uuid = '" + entityUUID.toString() + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                hasProfile = true;
            }
            result.close();
            sql.close();
            manager.release("mineSuiteEconomy", conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasProfile;
    }


    public static boolean loadSettings() {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        try {
            Connection conn = manager.getConnection("mineSuiteEconomy");
            PreparedStatement sql = conn.prepareStatement("SELECT setting, value FROM module_economy_settings;");
            ResultSet result = sql.executeQuery();
            while (result.next()) {
                String setting = result.getString("setting");
                String value = result.getString("value");
                EconomyManager.addSetting(setting, value);
            }
            result.close();
            sql.close();
            manager.release("mineSuiteEconomy", conn);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
