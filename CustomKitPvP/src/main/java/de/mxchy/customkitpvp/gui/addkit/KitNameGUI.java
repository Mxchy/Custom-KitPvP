package de.mxchy.customkitpvp.gui.addkit;

import de.mxchy.customkitpvp.CustomKitPvP;
import de.mxchy.customkitpvp.guilogic.addkit.AddKitGUILogic;
import de.mxchy.customkitpvp.kit.KitManager;
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

public class KitNameGUI {
    public static void createKitNameGUI(Player player){
        Inventory gui = Bukkit.createInventory(player,9,"Put an item with the name of the kit in the free slot");

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


        String editingName = (AddKitGUILogic.currentKitName.get(player.getUniqueId()) == null)?null: AddKitGUILogic.currentKitName.get(player.getUniqueId());
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(editingName);
        stack.setItemMeta(meta);

        for (int i = 0; i<9 ;i++){
            gui.setItem(i,pane);
        }

        gui.setItem(8,confirm);
        if(editingName == null) {
            gui.setItem(4, null);
        }else {
            gui.setItem(4, stack);
        }

        player.openInventory(gui);
    }
}
