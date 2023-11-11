package de.mxchy.customkitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryLeaveListener implements Listener {
    @EventHandler
    void onCloseInventory(InventoryCloseEvent event){
        /*
        String title = event.getView().getTitle();
        if(title.equals("Create Kit")||title.equals("Set the Armor of the Kit")||title.equals("Set the Display Item of the Kit")
        ||title.equals("Put an Item with the Name of the Kit in the free slot")||title.equals("Put the Items of the Kit here")){
            AddKitGUILogic.resetCurrentlyAddingKit();
        }

         */
    }
}
