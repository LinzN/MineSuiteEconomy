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

package de.linzn.mineSuite.economy;


import de.linzn.mineSuite.economy.commands.Money;
import de.linzn.mineSuite.economy.mysql.EconomyQuery;
import de.linzn.mineSuite.economy.utils.MineEconomyHandler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyPlugin extends JavaPlugin {
    private static EconomyPlugin inst;


    public static EconomyPlugin inst() {
        return inst;
    }

    @Override
    public void onLoad() {
        getServer().getServicesManager().register(Economy.class, new MineEconomyHandler(), this, ServicePriority.Highest);
    }

    @Override
    public void onEnable() {
        inst = this;
        if (EconomyQuery.loadSettings()) {
            loadCommands();
        } else {
            this.getLogger().severe("Database Error");
        }
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregister(Economy.class);
    }

    private void loadCommands() {
        Money moneyClass = new Money(this);
        if (!moneyClass.isLoaded())
            moneyClass.loadCmd();
        getLogger().info("Register command /money");
        getCommand("money").setExecutor(moneyClass);

    }
}
