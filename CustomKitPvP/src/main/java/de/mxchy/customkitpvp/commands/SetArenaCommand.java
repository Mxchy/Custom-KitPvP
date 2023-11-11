package de.mxchy.customkitpvp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(  CommandSender  sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        ItemStack coordHoe = new ItemStack(Material.WOODEN_HOE);

        //Setting the Item Meta
        ItemMeta meta = coordHoe.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§4§lSet the Coordinates of the PvP Arena");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("left click the hoe to set the first coordinate point");
        lore.add("right click the hoe to set the second coordinate point");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DAMAGE_ALL,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        coordHoe.setItemMeta(meta);


        player.getInventory().addItem(coordHoe);
        return true;
    }
}
