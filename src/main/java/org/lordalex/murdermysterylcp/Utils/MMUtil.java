package org.lordalex.murdermysterylcp.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lordalex.murdermysterylcp.Config.Config;

import static org.lordalex.murdermysterylcp.Config.YmlParser.parseLocation;

public class MMUtil {
    private static void spawnGoldResourse(CommandSender sender, Config config){
        Player p = (Player) sender;
        World world = p.getWorld();

        for (String position : config.getGoldSpawns()) {
            Location location = parseLocation(world, position);
            Item dropitem = world.dropItem(location, new ItemStack(Material.GOLD_INGOT, 1));
            dropitem.setVelocity(dropitem.getVelocity().zero());
        }
    }
}
