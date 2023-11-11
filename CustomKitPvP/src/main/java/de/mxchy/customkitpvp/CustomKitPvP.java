package de.mxchy.customkitpvp;

import de.mxchy.customkitpvp.commands.AddNewKitCommand;
import de.mxchy.customkitpvp.commands.DeleteKitCommand;
import de.mxchy.customkitpvp.commands.SelectKitCommand;
import de.mxchy.customkitpvp.commands.SetArenaCommand;
import de.mxchy.customkitpvp.config.InventoryConfig;
import de.mxchy.customkitpvp.enchants.Glow;
import de.mxchy.customkitpvp.guilogic.addkit.AddKitGUILogic;
import de.mxchy.customkitpvp.kit.KitManager;
import de.mxchy.customkitpvp.listeners.InventoryLeaveListener;
import de.mxchy.customkitpvp.listeners.ItemInteractListener;
import de.mxchy.customkitpvp.listeners.MenuClickListener;
import de.mxchy.customkitpvp.listeners.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Objects;

public final class CustomKitPvP extends JavaPlugin {
    private static CustomKitPvP plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        registerCommands();
        registerEvents();
        loadConfigFiles();
        registerEnchants();


        AddKitGUILogic.initializeKitCreation();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    void registerCommands(){
        Objects.requireNonNull(getCommand("setarena")).setExecutor(new SetArenaCommand());
        Objects.requireNonNull(getCommand("addnewkit")).setExecutor(new AddNewKitCommand());
        Objects.requireNonNull(getCommand("selectkit")).setExecutor(new SelectKitCommand());
        Objects.requireNonNull(getCommand("deletekit")).setExecutor(new DeleteKitCommand());
    }
    void registerEvents(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ItemInteractListener(),this);
        pluginManager.registerEvents(new PlayerMoveListener(),this);
        pluginManager.registerEvents(new MenuClickListener(),this);
        pluginManager.registerEvents(new InventoryLeaveListener(),this);
    }
    void loadConfigFiles(){
        KitManager.get().loadKits();
        InventoryConfig.get().loadPlayerInventories();

    }
    public static CustomKitPvP getPlugin(){
        return plugin;
    }
    void registerEnchants(){
        registerEnchantment(new Glow());
    }



    void registerEnchantment(Enchantment enchantment){


        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }



}
