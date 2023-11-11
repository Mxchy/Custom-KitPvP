package de.mxchy.customkitpvp.guilogic.addkit;

import de.mxchy.customkitpvp.gui.addkit.AddKitGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitItemsGUILogic {
    public static void kitItemsGUILogic(InventoryClickEvent e){

        ItemStack stack = e.getCurrentItem();
        if(stack == null){
            return;
        }


        if(stack.getType() == Material.GREEN_TERRACOTTA) {
            Inventory inventory = e.getClickedInventory();
            assert inventory != null;
            ItemStack[] items = new ItemStack[36];

            //Sets the items in the correct order to be put into the players inventory later
            for(int i = 0; i<9 ;i++){

                items[i] = inventory.getItem(i+36);
            }
            for(int i = 9; i<36;i++){
                items[i] = inventory.getItem(i-9);
            }



            AddKitGUILogic.currentKitItems.put(e.getWhoClicked().getUniqueId(),items);
            AddKitGUI.createAddKitGUI((Player)e.getWhoClicked());

        }

    }
}
