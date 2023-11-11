package de.mxchy.customkitpvp.commands;

import de.mxchy.customkitpvp.gui.deletekit.DeleteKitGUI;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(KitManager.get().getKitArrayList()== null){
            commandSender.sendMessage("There are no kits to delete yet!");
        }
        else {
            DeleteKitGUI.createDeleteKitGUI((Player) commandSender);
        }
        return true;
    }
}
