package de.mxchy.customkitpvp.guilogic.addkit;

import de.mxchy.customkitpvp.gui.addkit.AddKitGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class NameKitGUILogic {

    public static void kitNameItemGUILogic(InventoryClickEvent e){

        ItemStack stack = e.getCurrentItem();
        if(stack == null){
            return;
        }


        if(stack.getType() == Material.GREEN_TERRACOTTA) {
            Inventory inventory = e.getClickedInventory();

            assert inventory != null;
            ItemStack displayItem = inventory.getItem(4);


            if (displayItem != null){
                ItemMeta meta = displayItem.getItemMeta();


                assert meta != null;
                AddKitGUILogic.currentKitName .put(e.getWhoClicked().getUniqueId(),meta.getDisplayName());
                AddKitGUI.createAddKitGUI((Player)e.getWhoClicked());

            }
            else {
                e.getWhoClicked().sendMessage("You need to set the name of the kit first!");
            }


        }

    }
}
