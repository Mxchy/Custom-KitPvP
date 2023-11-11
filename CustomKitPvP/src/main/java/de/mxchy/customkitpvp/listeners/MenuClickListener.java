package de.mxchy.customkitpvp.listeners;

import de.mxchy.customkitpvp.CustomKitPvP;
import de.mxchy.customkitpvp.guilogic.addkit.*;
import de.mxchy.customkitpvp.guilogic.deletekit.DeleteKitGUILogic;
import de.mxchy.customkitpvp.guilogic.selectkit.SelectKitGUILogic;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.Objects;

public class MenuClickListener implements Listener {
    @EventHandler
    public void OnMenuClick(InventoryClickEvent e) throws IOException {

        if(e.getCurrentItem() == null){
            return;
        }
        ItemMeta meta = e.getCurrentItem().getItemMeta();
        if(meta != null){
            if(meta.getPersistentDataContainer().get(new NamespacedKey(CustomKitPvP.getPlugin(), "isUnmovableInInventory"), PersistentDataType.BOOLEAN) != null){
            e.setCancelled(true);
            }
        }

        String title = e.getView().getTitle();
        switch (title) {
            case "Create kit":
                AddKitGUILogic.addKitGUILogic(e);
                break;
            case "Set the armor of the kit":
                KitArmorGUILogic.kitArmorGUILogic(e);
                break;
            case "Set the displayitem of the kit":
                KitDisplayItemGUILogic.kitDisplayItemGUILogic(e);
                break;
            case "Put an item with the name of the kit in the free slot":
                NameKitGUILogic.kitNameItemGUILogic(e);
                break;
            case "Put the items of the kit here":
                KitItemsGUILogic.kitItemsGUILogic(e);
                break;
            case "Select a kit!":
                SelectKitGUILogic.selectKitGUILogic(e);
                break;
            case "Click on a kit to delete!":
                DeleteKitGUILogic.deleteKitGUILogic(e);
        }


    }
}
