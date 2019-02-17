package de.linzn.mineSuite.economy.mysql;

import de.linzn.mineSuite.core.database.mysql.MySQLConnectionManager;
import de.linzn.mineSuite.economy.api.EconomyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EconomyQuery {

    public static UUID getEconomyProfile(String player) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        UUID uuid = null;

        try {
            Connection conn = manager.getConnection("mineSuiteEconomy");
            PreparedStatement sql = conn.prepareStatement("SELECT UUID FROM core_uuidcache WHERE NAME = '" + player + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                uuid = UUID.fromString(result.getString(1));
            }
            result.close();
            sql.close();
            manager.release("mineSuiteEconomy", conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uuid;
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
