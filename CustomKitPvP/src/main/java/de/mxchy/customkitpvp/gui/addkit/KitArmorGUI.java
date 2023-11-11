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

public class KitArmorGUI {
    public static void createKitArmorGUI(Player player){
        Inventory gui = Bukkit.createInventory(player,27,"Set the armor of the kit");

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        paneMeta.setDisplayName(ChatColor.BLUE+"");
        paneMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        pane.setItemMeta(paneMeta);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        assert chestplateMeta != null;
        chestplateMeta.setDisplayName("Drag the chestplate under this slot");
        chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);

        chestplate.setItemMeta(chestplateMeta);

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        assert helmetMeta != null;
        helmetMeta.setDisplayName("Drag the helmet under this slot");
        helmetMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        helmet.setItemMeta(helmetMeta);

        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta leggingsMeta = leggings.getItemMeta();
        assert leggingsMeta != null;
        leggingsMeta.setDisplayName("Drag the leggings under this slot");
        leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        leggings.setItemMeta(leggingsMeta);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        assert bootsMeta != null;
        bootsMeta.setDisplayName("Drag the boots under this slot");
        bootsMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        boots.setItemMeta(bootsMeta);

        ItemStack confirm = new ItemStack(Material.GREEN_TERRACOTTA);
        ItemMeta confirmMeta = confirm.getItemMeta();
        assert confirmMeta != null;
        confirmMeta.setDisplayName("Click to confirm"+ChatColor.GREEN);
        confirmMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        confirm.setItemMeta(confirmMeta);



        ItemStack[] currentEditingArmor = new ItemStack[4];
        if(AddKitGUILogic.currentKitArmorContents.get(player.getUniqueId()) != null){
            currentEditingArmor = AddKitGUILogic.currentKitArmorContents.get(player.getUniqueId());
        }




        for (int i = 0; i<27 ;i++){
            gui.setItem(i,pane);
        }
        gui.setItem(1,helmet);
        gui.setItem(3,chestplate);
        gui.setItem(5,leggings);
        gui.setItem(7,boots);
        gui.setItem(26,confirm);
        gui.setItem(10,currentEditingArmor[3]);
        gui.setItem(12,currentEditingArmor[2]);
        gui.setItem(14,currentEditingArmor[1]);
        gui.setItem(16,currentEditingArmor[0]);

        player.openInventory(gui);
    }
}

