package de.mxchy.customkitpvp.kit;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Kit {
    private String name;
    private ItemStack displayItem;

    private ItemStack[] armorContent;
    private ItemStack[] inventoryContent;
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public Kit(UUID uuid, String name, ItemStack displayItem, ItemStack[] armorContent, ItemStack[] inventoryContent) {

        this.uuid = uuid;
        this.name = name;
        this.displayItem = displayItem;

        this.armorContent = armorContent;
        this.inventoryContent = inventoryContent;
    }

    public String getName() {
        return name;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public ItemStack[] getArmorContent() {
        return armorContent;
    }

    public ItemStack[] getInventoryContent() {
        return inventoryContent;
    }
}
