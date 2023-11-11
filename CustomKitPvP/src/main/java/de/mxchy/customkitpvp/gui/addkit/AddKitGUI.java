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

import java.util.ArrayList;
import java.util.List;

public class AddKitGUI {
    public static void createAddKitGUI(Player p){
        Inventory gui = Bukkit.createInventory(p,27,"Create kit");

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta paneMeta = pane.getItemMeta();

        assert paneMeta != null;
        paneMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        paneMeta.setDisplayName(ChatColor.BLUE+"");
        paneMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        pane.setItemMeta(paneMeta);


        ItemStack kitNameStack = new ItemStack(Material.OAK_SIGN);
        ItemMeta kitNameMeta = kitNameStack.getItemMeta();
        assert kitNameMeta != null;
        kitNameMeta.setDisplayName( "Click to set the kit's name");
        List<String> kitNameLore = new ArrayList<>();
        if(AddKitGUILogic.currentKitName.get(p.getUniqueId()) == null) {
            kitNameLore.add("Currently selected kit name: none");
        }
        else{
            kitNameLore.add("Currently selected kit name: "+AddKitGUILogic.currentKitName.get(p.getUniqueId()));
        }
        kitNameMeta.setLore(kitNameLore);
        kitNameMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        kitNameStack.setItemMeta(kitNameMeta);

        ItemStack kitDisplay = new ItemStack(Material.ITEM_FRAME);
        ItemMeta kitDisplayMeta = kitDisplay.getItemMeta();
        assert kitDisplayMeta != null;
        kitDisplayMeta.setDisplayName("Click to set the kit's display item");
        List<String> kitDisplayLore = new ArrayList<>();
        if(AddKitGUILogic.currentDisplayItem.get(p.getUniqueId()) == null){
            kitDisplayLore.add("Currently selected kit display item: none");
        }
        else{
            kitDisplayLore.add("Currently selected kit display item: "+AddKitGUILogic.currentDisplayItem.get(p.getUniqueId()).getType().name());
        }
        kitDisplayMeta.setLore(kitDisplayLore);
        kitDisplayMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        kitDisplay.setItemMeta(kitDisplayMeta);

        ItemStack kitArmor = new ItemStack(Material.ARMOR_STAND);
        ItemMeta kitArmorMeta = kitArmor.getItemMeta();
        assert kitArmorMeta != null;
        kitArmorMeta.setDisplayName("Click to set the kit's armor");
        kitArmorMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        kitArmor.setItemMeta(kitArmorMeta);

        ItemStack kitInventory = new ItemStack(Material.CHEST);
        ItemMeta kitInventoryItemMeta = kitInventory.getItemMeta();
        assert kitInventoryItemMeta != null;
        kitInventoryItemMeta.setDisplayName("Click to set the kit's inventory content");
        kitInventoryItemMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        kitInventory.setItemMeta(kitInventoryItemMeta);

        ItemStack createKit = new ItemStack(Material.GREEN_TERRACOTTA);
        ItemMeta createKitMeta = createKit.getItemMeta();
        assert createKitMeta != null;
        createKitMeta.setDisplayName("Create Kit");
        createKitMeta.getPersistentDataContainer().set(new NamespacedKey(CustomKitPvP.getPlugin(),"isUnmovableInInventory"), PersistentDataType.BOOLEAN,true);
        createKit.setItemMeta(createKitMeta);

        for (int i = 0; i<27 ;i++){
            gui.setItem(i,pane);
        }
        gui.setItem(10,kitNameStack);
        gui.setItem(12,kitDisplay);
        gui.setItem(14,kitArmor);
        gui.setItem(16,kitInventory);
        gui.setItem(26,createKit);

        p.openInventory(gui);
    }
}
