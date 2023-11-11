package de.mxchy.customkitpvp.listeners;

import de.mxchy.customkitpvp.config.KitConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.io.IOException;
import java.util.Objects;

public class ItemInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) throws IOException {
        if(Objects.equals(e.getHand(), EquipmentSlot.OFF_HAND)){
            return;
        }
        if(e.getItem() == null){
            return;
        }
        if (Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§4§lSet the Coordinates of the PvP Arena")){
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(KitConfig.get().setArenaCoord2(Objects.requireNonNull(e.getClickedBlock()).getLocation(),e.getPlayer())) {
                    e.getPlayer().sendMessage("You set the 2nd coordinate at: " + Objects.requireNonNull(e.getClickedBlock()).getX() + "," + e.getClickedBlock().getY() + "," + e.getClickedBlock().getZ());
                }

            }
            else if(e.getAction() == Action.LEFT_CLICK_BLOCK){
                if(KitConfig.get().setArenaCoord1(Objects.requireNonNull(e.getClickedBlock()).getLocation(),e.getPlayer())) {

                    e.getPlayer().sendMessage("You set the 1nd coordinate at: " + Objects.requireNonNull(e.getClickedBlock()).getX() + "," + e.getClickedBlock().getY() + "," + e.getClickedBlock().getZ());
                }
            }
            e.setCancelled(true);
        }

    }

}

