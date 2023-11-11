package de.mxchy.customkitpvp.guilogic.selectkit;


import de.mxchy.customkitpvp.gui.selectkit.SelectKitGUI;
import de.mxchy.customkitpvp.kit.Kit;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.Objects;


public class SelectKitGUILogic {
    public static void selectKitGUILogic(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Kit clickedKit = null;
        Material material = Objects.requireNonNull(e.getCurrentItem()).getType();
        for (Kit kit: KitManager.get().getKitArrayList()) {
            if(kit.getDisplayItem().getType().equals(material)){
                clickedKit = kit;
            }
        }
        if(clickedKit != null) {
            KitManager.get().setSelectedKit(clickedKit, player);
            player.sendMessage("You selected the "+clickedKit.getName()+ " kit");



            SelectKitGUI.createSelectKitGUI(player);

        }
        e.setCancelled(true);

    }
}
