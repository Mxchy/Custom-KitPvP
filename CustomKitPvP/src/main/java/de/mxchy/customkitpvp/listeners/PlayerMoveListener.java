package de.mxchy.customkitpvp.listeners;

import de.mxchy.customkitpvp.config.KitConfig;
import de.mxchy.customkitpvp.config.InventoryConfig;
import de.mxchy.customkitpvp.kit.Kit;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


import java.util.Objects;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (KitConfig.get().checkIfInArena(Objects.requireNonNull(e.getTo())) && !KitConfig.get().checkIfInArena(e.getFrom())) {

            p.sendMessage(ChatColor.GREEN + "You entered the PvP area!");
            InventoryConfig.get().addPlayerInventory(p);

            Kit selectedKit = KitManager.get().getSelectedKit(p);
            if(selectedKit != null) {
                KitManager.get().equipKit(p, selectedKit);
            }
            else {
                p.sendMessage(ChatColor.RED + "You entered the PvP area but didn't select a kit yet! Use /selectkit to select one");
                p.getInventory().clear();
            }






        } else if (!KitConfig.get().checkIfInArena(e.getTo()) && KitConfig.get().checkIfInArena(e.getFrom())) {
            p.sendMessage(ChatColor.GREEN + "You left the PvP area!");
            InventoryConfig.get().equipPlayerInventory(p);
            InventoryConfig.get().deleteSavedPlayerInventory(p);

        }
    }
}
