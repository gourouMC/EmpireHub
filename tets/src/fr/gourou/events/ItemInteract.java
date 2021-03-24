package fr.gourou.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class ItemInteract implements Listener{
	@EventHandler
	 public void onInteract(PlayerInteractEvent e){
	        Player p = e.getPlayer();
	        if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
	 
	        switch(p.getInventory().getItemInHand().getType()){
	            case COMPASS:
	            	  Inventory inv = Bukkit.createInventory(null, 9, "Mini-jeux");
	            	  ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);
	            	  ItemMeta metaPane = pane.getItemMeta();
	            	  metaPane.setDisplayName("§cNothing here now");
	            	  pane.setItemMeta(metaPane);
	            	  inv.setItem(0, pane);
	            	  inv.setItem(2, pane);
	            	  inv.setItem(3, pane);
	            	  inv.setItem(4, pane);
	            	  inv.setItem(5, pane);
	            	  inv.setItem(6, pane);
	            	  inv.setItem(7, pane);
	            	  inv.setItem(8, pane);
	            	  
	            	  ItemStack practice = new ItemStack(Material.DIAMOND_SWORD);
	            	  ItemMeta metaPractice = practice.getItemMeta();
	            	  metaPractice.setDisplayName("§6Practice");
	            	  practice.setItemMeta(metaPractice);
	            	  inv.setItem(1, practice);
	            	  
	            	  ItemStack skywars = new ItemStack(Material.FEATHER);
	            	  ItemMeta metaSkywars = skywars.getItemMeta();
	            	  metaSkywars.setDisplayName("§5Flight duel");
	            	  skywars.setItemMeta(metaSkywars);
	            	  inv.setItem(3, skywars);
	                  p.openInventory(inv);
	                  p.updateInventory();
	                break;
	            case EMERALD:
	            	Inventory invCosm = Bukkit.createInventory(null, 18, "Cosmétiques");
	            	  ItemStack partical = new ItemStack(Material.REDSTONE);
	            	  ItemMeta metaPartical = partical.getItemMeta();
	            	  metaPartical.setDisplayName("§6Particule");
	            	  partical.setItemMeta(metaPartical);
	            	  invCosm.setItem(3, partical);
	                  p.openInventory(invCosm);
	                  p.updateInventory();
	            	break;
	            default: break;
	        }
	    }
}
