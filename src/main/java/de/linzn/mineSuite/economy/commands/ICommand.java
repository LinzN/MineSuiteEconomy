package de.linzn.mineSuite.economy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand {
    boolean runCmd(Command cmd, CommandSender sender, String[] args);
}
