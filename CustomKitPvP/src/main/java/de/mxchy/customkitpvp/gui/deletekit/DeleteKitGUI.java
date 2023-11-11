package de.mxchy.customkitpvp.gui.deletekit;


import de.mxchy.customkitpvp.CustomKitPvP;
import de.mxchy.customkitpvp.kit.Kit;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class DeleteKitGUI {
    public static void createDeleteKitGUI(Player p){
        ArrayList<Kit> kitArrayList= KitManager.get().getKitArrayList();

        int inventorySize =  (kitArrayList.size()>9) ? (int)Math.ceil(kitArrayList.size()/9.0) : 9;
        Inventory gui;
        gui = Bukkit.createInventory(p,inventorySize,"Click on a kit to delete!");

        for (Kit kit : kitArrayList) {

            ItemStack stack = new ItemStack(kit.getDisplayItem().getType());
            ItemMeta meta = stack.getItemMeta();
            assert meta != null;
            meta.setDisplayName(kit.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("click to delete the "+kit.getName()+" kit");
            meta.setLore(lore);
            meta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);

            stack.setItemMeta(meta);

            gui.addItem(stack);
            p.openInventory(gui);



        }
    }
}
