package de.mxchy.customkitpvp.kit;

import de.mxchy.customkitpvp.config.KitConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KitManager {


    private  ArrayList<Kit> kitArrayList = new ArrayList<>();


    private HashMap<UUID /*Player UUID*/,UUID /*Kit UUID*/> selectedKitsMap = new HashMap<>();

    private static KitManager instance;
    private KitManager(){}

    public  ArrayList<Kit> getKitArrayList() {
        return kitArrayList;
    }

    public static KitManager get() {
        if(instance == null){
            instance = new KitManager();
        }
        return instance;
    }
    public void equipKit(Player player,Kit kit){
        player.getInventory().clear();
        if(kit.getInventoryContent() != null) {
            player.getInventory().setContents(kit.getInventoryContent());
        }
        if(kit.getArmorContent() != null) {
            player.getInventory().setArmorContents(kit.getArmorContent());

        }
    }
    public  void createNewKit(String displayName,ItemStack displayItem,ItemStack[] armor, ItemStack[] items ) {
        Kit kit = new Kit(UUID.randomUUID(),displayName,displayItem,armor,items);
        kitArrayList.add(kit);
        KitConfig.get().saveKitList(kitArrayList);

    }
    //Must be called on initialize
    public void loadKits(){
       kitArrayList = KitConfig.get().getSavedKits();
       selectedKitsMap = KitConfig.get().getSelectedKits();

    }
    public void deleteKit(Kit kit){




        for (Map.Entry<UUID,UUID> entry: selectedKitsMap.entrySet()) {
            UUID key = entry.getKey();
            UUID value = entry.getValue();
            if(value.equals(kit.getUuid())){
                selectedKitsMap.remove(key);
            }
        }



        kitArrayList.remove(kit);

        KitConfig.get().saveKitList(kitArrayList);
        KitConfig.get().saveSelectedKits(selectedKitsMap);
    }

    public Kit getSelectedKit(Player p) {
        UUID uuid = selectedKitsMap.get(p.getUniqueId());
        for(Kit kit: kitArrayList){
            if (kit.getUuid().equals(uuid)){
                return kit;
            }
        }
        return null;
    }
    public void setSelectedKit(Kit kit, Player player){
        selectedKitsMap.remove(player.getUniqueId());
        selectedKitsMap.put(player.getUniqueId(),kit.getUuid());
        KitConfig.get().saveSelectedKits(selectedKitsMap);
    }

}
