package de.mxchy.customkitpvp.guilogic.deletekit;

import de.mxchy.customkitpvp.gui.selectkit.SelectKitGUI;
import de.mxchy.customkitpvp.kit.Kit;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class DeleteKitGUILogic {

    public static void deleteKitGUILogic(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Kit clickedKit = null;
        Material material = Objects.requireNonNull(e.getCurrentItem()).getType();
        for (Kit kit: KitManager.get().getKitArrayList()) {
            if(kit.getDisplayItem().getType().equals(material)){
                clickedKit = kit;
            }
        }
        if(clickedKit != null) {
            KitManager.get().deleteKit(clickedKit);
            e.getWhoClicked().sendMessage("Successfully deleted the "+clickedKit.getName()+" kit");
            e.getWhoClicked().closeInventory();
        }
        e.setCancelled(true);

    }

}
