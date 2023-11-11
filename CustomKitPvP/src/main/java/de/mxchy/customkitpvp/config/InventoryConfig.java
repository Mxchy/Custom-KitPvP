package de.mxchy.customkitpvp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class InventoryConfig {
    private static File file = new File("plugins/kitpvp","InventoryConfig.yml");;
    private static FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);;
    private static InventoryConfig instance;
    HashMap<UUID, ItemStack[]> playerInventories = new HashMap<>();
    HashMap<UUID, ItemStack[]> playerArmorContents = new HashMap<>();

    private InventoryConfig(){}
    public static InventoryConfig get(){
        if(instance == null ){
            instance = new InventoryConfig();
        }
        return instance;
    }


     void save(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
    public void addPlayerInventory(Player p){
        ItemStack[] playerInventory = p.getInventory().getContents();
        ItemStack[] playerArmorContent = p.getInventory().getArmorContents();
        playerInventories.put(p.getUniqueId(),playerInventory);
        playerArmorContents.put(p.getUniqueId(),playerArmorContent);

        fileConfiguration.set("PlayersInArenaInventories",ConfigUtil.objectToBase64(playerInventories));
        fileConfiguration.set("PlayersInArenaArmorContents",ConfigUtil.objectToBase64(playerArmorContents));


        save();

    }

    public void equipPlayerInventory(Player player){
        player.getInventory().setContents(playerInventories.get(player.getUniqueId()));
        player.getInventory().setArmorContents(playerArmorContents.get(player.getUniqueId()));

    }
    public void deleteSavedPlayerInventory(Player player){
        playerInventories.remove(player.getUniqueId());
        playerArmorContents.remove(player.getUniqueId());

        fileConfiguration.set("PlayersInArenaInventories",ConfigUtil.objectToBase64(playerInventories));
        fileConfiguration.set("PlayersInArenaArmorContents",ConfigUtil.objectToBase64(playerArmorContents));
        save();
    }

    //Needs to be called on initialize
    public void loadPlayerInventories(){
        playerInventories = (HashMap<UUID, ItemStack[]>) ConfigUtil.base64ToObject( fileConfiguration.getString("PlayersInArenaInventories"));
        playerArmorContents = (HashMap<UUID, ItemStack[]>) ConfigUtil.base64ToObject(fileConfiguration.getString("PlayersInArenaArmorContents"));





    }


}


