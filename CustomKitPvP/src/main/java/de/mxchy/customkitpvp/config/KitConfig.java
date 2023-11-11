package de.mxchy.customkitpvp.config;

import de.mxchy.customkitpvp.kit.Kit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class KitConfig {
    public static File kitConfigFile = new File("plugins/kitpvp", "kitconfig.yml");
    public static FileConfiguration kitConfig = YamlConfiguration.loadConfiguration(kitConfigFile);

    private static KitConfig instance;
    private KitConfig(){}
    public static KitConfig get(){
        if(instance == null){
            instance = new KitConfig();
        }
        return instance;
    }

    void save()  {
        try {
            kitConfig.save(kitConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public  boolean setArenaCoord1(Location loc, Player p)  {
        String worldName = (String) kitConfig.get("arena.arena_pos2.world");
        if(worldName != null) {
            if (!worldName.equals(Objects.requireNonNull(loc.getWorld()).getName())) {
                p.sendMessage("The Coordinates have to be in the same world");
                return false;
            }
        }

        kitConfig.set("arena.arena_pos1.x", loc.getBlock().getLocation().getX());
        kitConfig.set("arena.arena_pos1.y", loc.getBlock().getLocation().getY());
        kitConfig.set("arena.arena_pos1.z", loc.getBlock().getLocation().getZ());
        kitConfig.set("arena.arena_pos1.world", Objects.requireNonNull(loc.getWorld()).getName());


        save();
        return true;
    }
    public  boolean setArenaCoord2(Location loc, Player p) {
        String worldName = (String) kitConfig.get("arena.arena_pos1.world");
        if (worldName != null) {
            if (!worldName.equals(Objects.requireNonNull(loc.getWorld()).getName())) {
                p.sendMessage("The Coordinates have to be in the same world");
                return false;
            }
        }

        kitConfig.set("arena.arena_pos2.x", loc.getBlock().getLocation().getX());
        kitConfig.set("arena.arena_pos2.y", loc.getBlock().getLocation().getY());
        kitConfig.set("arena.arena_pos2.z", loc.getBlock().getLocation().getZ());
        kitConfig.set("arena.arena_pos2.world", Objects.requireNonNull(loc.getWorld()).getName());

        save();
        return true;
    }
    public  boolean checkIfInArena(Location playerLocation) {
        int playerLocationX = playerLocation.getBlockX();
        int playerLocationY = playerLocation.getBlockY();
        int playerLocationZ = playerLocation.getBlockZ();


        int loc1X = kitConfig.getInt("arena.arena_pos1.x");
        int loc1Y = kitConfig.getInt("arena.arena_pos1.y");
        int loc1Z = kitConfig.getInt("arena.arena_pos1.z");



        int loc2X = kitConfig.getInt("arena.arena_pos2.x");
        int loc2Y = kitConfig.getInt("arena.arena_pos2.y");
        int loc2Z = kitConfig.getInt("arena.arena_pos2.z");


        return intInBetween(playerLocationX,loc1X,loc2X) && intInBetween(playerLocationY,loc1Y,loc2Y) && intInBetween(playerLocationZ,loc1Z,loc2Z);

    }
    boolean intInBetween(int number, int value1, int value2){
        int minValue = Math.min(value1,value2);
        int maxValue = Math.max(value1,value2);
        return number >= minValue && number <= maxValue;
    }
    public  void saveKitList(ArrayList<Kit> kitArrayList)  {
        ArrayList<UUID> kitUUIDList = new ArrayList<>();
        ArrayList<String>  kitNameList= new ArrayList<>();
        ArrayList<ItemStack> kitDisplayItemList = new ArrayList<>();
        ArrayList<ItemStack[]> kitArmorContentList = new ArrayList<>();
        ArrayList<ItemStack[]> kitInventoryContentList = new ArrayList<>();

        for (Kit kit : kitArrayList) {
            kitUUIDList.add(kit.getUuid());
            kitNameList.add(kit.getName());
            kitDisplayItemList.add(kit.getDisplayItem());
            kitArmorContentList.add(kit.getArmorContent());
            kitInventoryContentList.add(kit.getInventoryContent());
        }

        kitConfig.set("KitUUIDList",ConfigUtil.objectToBase64(kitUUIDList));
        kitConfig.set("KitNameList",ConfigUtil.objectToBase64(kitNameList));
        kitConfig.set("KitDisplayItemList",ConfigUtil.objectToBase64(kitDisplayItemList));
        kitConfig.set("KitArmorContentList",ConfigUtil.objectToBase64(kitArmorContentList));
        kitConfig.set("KitInventoryContentList",ConfigUtil.objectToBase64(kitInventoryContentList));
        save();

    }
    public ArrayList<Kit> getSavedKits() {
        ArrayList<Kit> kitArrayList= new ArrayList<>();

        if(kitConfig.get("KitNameList") !=null) {
            ArrayList<UUID> kitUUIDList = (ArrayList<UUID>) ConfigUtil.base64ToObject(kitConfig.getString("KitUUIDList"));
            ArrayList<String> kitNameList = (ArrayList<String>) ConfigUtil.base64ToObject( kitConfig.getString("KitNameList"));
            ArrayList<ItemStack> kitDisplayItemList = (ArrayList<ItemStack>) ConfigUtil.base64ToObject(kitConfig.getString("KitDisplayItemList"));
            ArrayList<ItemStack[]> kitArmorContentList = (ArrayList<ItemStack[]>) ConfigUtil.base64ToObject( kitConfig.getString("KitArmorContentList"));
            ArrayList<ItemStack[]> kitInventoryContentList = (ArrayList<ItemStack[]>) ConfigUtil.base64ToObject( kitConfig.getString("KitInventoryContentList"));



            for (int i = 0; i < kitNameList.size(); i++) {
                kitArrayList.add(new Kit(kitUUIDList.get(i),kitNameList.get(i), kitDisplayItemList.get(i), kitArmorContentList.get(i), kitInventoryContentList.get(i)));
            }
        }
        return kitArrayList;
    }
    public HashMap<UUID,UUID> getSelectedKits(){
        HashMap<UUID,UUID> map = new HashMap<>();


        if(kitConfig.get("SelectedKitList") !=null ){
            map = (HashMap<UUID, UUID>) ConfigUtil.base64ToObject(kitConfig.getString("SelectedKitList"));
        }





        return map;
    }
    public void saveSelectedKits(HashMap<UUID,UUID> selectedKits){
        kitConfig.set("SelectedKitList",ConfigUtil.objectToBase64(selectedKits));
        save();

    }



}
