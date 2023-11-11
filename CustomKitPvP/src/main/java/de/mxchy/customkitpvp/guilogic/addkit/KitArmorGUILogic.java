package de.mxchy.customkitpvp.guilogic.addkit;

import de.mxchy.customkitpvp.gui.addkit.AddKitGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitArmorGUILogic {
    public static void kitArmorGUILogic(InventoryClickEvent e){

        ItemStack stack = e.getCurrentItem();
        if(stack == null){
            return;
        }


        if(stack.getType() == Material.GREEN_TERRACOTTA){
            Inventory inventory = e.getClickedInventory();

            assert inventory != null;
            ItemStack helmet = inventory.getItem(10);
            ItemStack chestplate = inventory.getItem(12);
            ItemStack leggings = inventory.getItem(14);
            ItemStack boots = inventory.getItem(16);

            ItemStack[] armorContent = new ItemStack[4];

            if(helmet != null )
                armorContent[3] = helmet;
            if(chestplate != null )
                armorContent[2] = chestplate;
            if(leggings != null )
                armorContent[1] = leggings;
            if(boots != null )
                armorContent[0] = boots;

            AddKitGUILogic.currentKitArmorContents.put(e.getWhoClicked().getUniqueId(),armorContent);
            AddKitGUI.createAddKitGUI((Player)e.getWhoClicked());

        }

    }
}
