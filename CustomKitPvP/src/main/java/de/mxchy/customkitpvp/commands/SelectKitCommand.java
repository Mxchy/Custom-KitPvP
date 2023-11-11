package de.mxchy.customkitpvp.commands;

import de.mxchy.customkitpvp.config.KitConfig;
import de.mxchy.customkitpvp.gui.selectkit.SelectKitGUI;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(KitManager.get().getKitArrayList() == null || KitManager.get().getKitArrayList().isEmpty()) {
            commandSender.sendMessage("There are no kits to select yet!");



        }
        else{
            if(KitConfig.get().checkIfInArena(((Player) commandSender).getLocation())){
                commandSender.sendMessage("You cannot use this command in this area!");

            }else {
                SelectKitGUI.createSelectKitGUI((Player) commandSender);
            }
        }
        return true;
    }
}
