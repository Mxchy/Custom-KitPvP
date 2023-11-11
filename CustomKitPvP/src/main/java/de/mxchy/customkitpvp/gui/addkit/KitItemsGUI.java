package de.mxchy.customkitpvp.gui.addkit;

import de.mxchy.customkitpvp.CustomKitPvP;
import de.mxchy.customkitpvp.guilogic.addkit.AddKitGUILogic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class KitItemsGUI {
    public static void createKitItemsGUI(Player player){
        Inventory gui = Bukkit.createInventory(player,54,"Put the items of the kit here");

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        paneMeta.setDisplayName(ChatColor.BLUE+"");
        paneMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        pane.setItemMeta(paneMeta);



        ItemStack confirm = new ItemStack(Material.GREEN_TERRACOTTA);
        ItemMeta confirmMeta = confirm.getItemMeta();
        assert confirmMeta != null;
        confirmMeta.setDisplayName("Click to confirm"+ChatColor.GREEN);
        confirmMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        confirm.setItemMeta(confirmMeta);


        ItemStack[] currentEditingItems = new ItemStack[36];
        if(AddKitGUILogic.currentKitItems.get(player.getUniqueId()) != null){
            currentEditingItems = AddKitGUILogic.currentKitItems.get(player.getUniqueId());
        }

        for(int i = 0; i<27;i++){
            gui.setItem(i,currentEditingItems[i+9]);
        }




        for (int i =27 ; i<36 ;i++){
            gui.setItem(i,pane);
        }
        for(int i = 36;i<45;i++){
            gui.setItem(i,currentEditingItems[i-36]);
        }
        for (int i = 45; i<53 ;i++){
            gui.setItem(i,pane);
        }

        gui.setItem(53,confirm);


        player.openInventory(gui);
    }
}
