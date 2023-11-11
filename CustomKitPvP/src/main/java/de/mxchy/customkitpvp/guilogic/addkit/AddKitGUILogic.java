package de.mxchy.customkitpvp.guilogic.addkit;


import de.mxchy.customkitpvp.gui.addkit.KitArmorGUI;
import de.mxchy.customkitpvp.gui.addkit.KitDisplayItemGUI;
import de.mxchy.customkitpvp.gui.addkit.KitItemsGUI;
import de.mxchy.customkitpvp.gui.addkit.KitNameGUI;
import de.mxchy.customkitpvp.kit.KitManager;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AddKitGUILogic {
    public static HashMap<UUID,ItemStack[]> currentKitArmorContents = new HashMap<>();
    public static HashMap<UUID,ItemStack[]> currentKitItems = new HashMap<>();
    public static HashMap<UUID,ItemStack> currentDisplayItem = new HashMap<>();
    public static HashMap<UUID,String> currentKitName = new HashMap<>();



    //MUST be called on initialization
    public static void initializeKitCreation(){
        currentKitArmorContents = new HashMap<>();
        currentKitItems = new HashMap<>();
        currentDisplayItem = new HashMap<>();
        currentKitName = new HashMap<>();

    }

    public static void addKitGUILogic(InventoryClickEvent e)  {
        ItemStack stack = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        switch (Objects.requireNonNull(stack).getType()){
            case OAK_SIGN:
                KitNameGUI.createKitNameGUI(player);
                break;
            case ARMOR_STAND:
                KitArmorGUI.createKitArmorGUI(player);
                break;
            case GREEN_TERRACOTTA:
                if(currentKitName.get(player.getUniqueId()) == null){
                    player.sendMessage("You still need to set the name of the kit");
                }else if(currentDisplayItem.get(player.getUniqueId()) == null){
                    player.sendMessage("You still need to set the display item of the kit");
                }else {
                    KitManager.get().createNewKit(currentKitName.get(player.getUniqueId()), currentDisplayItem.get(player.getUniqueId()), currentKitArmorContents.get(player.getUniqueId()), currentKitItems.get(player.getUniqueId()));
                    player.closeInventory();
                    player.sendMessage("Successfully created the " + currentKitName.get(player.getUniqueId()) + " kit");
                    resetCurrentlyAddingKit(player);
                }
                break;
            case ITEM_FRAME:
                KitDisplayItemGUI.createDisplayItemGUI(player);
                break;
            case CHEST:
                KitItemsGUI.createKitItemsGUI(player);
                break;


        }

    }
    public static void resetCurrentlyAddingKit(Player p){
        currentKitName.remove(p.getUniqueId());
        currentKitArmorContents.remove(p.getUniqueId());
        currentKitItems.remove(p.getUniqueId());
        currentDisplayItem.remove(p.getUniqueId());
    }


}
