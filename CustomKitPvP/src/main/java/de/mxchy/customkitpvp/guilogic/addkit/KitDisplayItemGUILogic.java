package de.mxchy.customkitpvp.guilogic.addkit;

import de.mxchy.customkitpvp.gui.addkit.AddKitGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitDisplayItemGUILogic {
    public static void kitDisplayItemGUILogic(InventoryClickEvent e){

        ItemStack stack = e.getCurrentItem();
        if(stack == null){
            return;
        }


        if(stack.getType() == Material.GREEN_TERRACOTTA) {
            Inventory inventory = e.getClickedInventory();

            assert inventory != null;
            ItemStack displayItem = inventory.getItem(4);


            if (displayItem != null){
                AddKitGUILogic.currentDisplayItem.put(e.getWhoClicked().getUniqueId(),displayItem);
                AddKitGUI.createAddKitGUI((Player)e.getWhoClicked());
            }
            else {
                e.getWhoClicked().sendMessage("You need to select a display-item first!");
            }



        }

    }
}
