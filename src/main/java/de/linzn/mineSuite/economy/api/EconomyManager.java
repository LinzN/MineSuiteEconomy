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

import java.util.HashMap;

public class EconomyManager {
    private static HashMap<String, String> settings = new HashMap<>();


    public static String getSetting(String setting) {
        return settings.get(setting);
    }

    public static void addSetting(String setting, String value) {
        settings.put(setting, value);
    }

}
