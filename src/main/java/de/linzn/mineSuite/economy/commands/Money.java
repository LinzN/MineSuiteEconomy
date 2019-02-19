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

package de.linzn.mineSuite.economy.commands;


import com.google.common.collect.Maps;
import de.linzn.mineSuite.economy.EconomyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Money implements CommandExecutor {

    public ThreadPoolExecutor cmdThread;
    private boolean isLoaded = false;
    private EconomyPlugin plugin;
    private TreeMap<String, ICommand> cmdMap = Maps.newTreeMap();

    public Money(EconomyPlugin plugin) {
        this.plugin = plugin;
        this.cmdThread = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, String label, final String[] args) {
        cmdThread.submit(() -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
            }
            if (args.length == 0) {
                getCmdMap().get("balance").runCmd(cmd, sender, args);
            } else if (getCmdMap().containsKey(args[0])) {
                String command = args[0];
                if (!getCmdMap().get(command).runCmd(cmd, sender, args)) {
                    sender.sendMessage("/money help");
                }
            } else {
                sender.sendMessage("/money help");
            }

        });
        return true;
    }

    public TreeMap<String, ICommand> getCmdMap() {
        return cmdMap;
    }

    public void loadCmd() {
        try {
            this.cmdMap.put("help", new MoneyHelp(this.plugin, "mineSuiteEconomy.user.help"));
            this.cmdMap.put("balance", new MoneyBalance(this.plugin, "mineSuiteEconomy.user.balance"));

            this.cmdMap.put("set", new MoneySet(this.plugin, "mineSuiteEconomy.admin.set"));

            this.isLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }
}
