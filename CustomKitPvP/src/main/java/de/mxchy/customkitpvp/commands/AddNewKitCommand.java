package de.mxchy.customkitpvp.commands;

import de.mxchy.customkitpvp.gui.addkit.AddKitGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddNewKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        AddKitGUI.createAddKitGUI((Player) commandSender);
        return true;
    }
}
