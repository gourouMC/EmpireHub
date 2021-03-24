package fr.gourou.events;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.gourou.manager.PlayerManager;

public class InventoryInteract implements Listener{
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null)return ;
		Player p = (Player) e.getWhoClicked();
		switch(e.getCurrentItem().getType()){
		 
        case DIAMOND_SWORD:
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Practice")){
                e.setCancelled(true);
                p.sendMessage("§cEnvoyez vers : §r" + e.getCurrentItem().getItemMeta().getDisplayName());
                p.closeInventory();
            }
            break;
        case FEATHER:
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Flight duel")){
                e.setCancelled(true);
                p.sendMessage("§cEnvoyez vers : §r" + e.getCurrentItem().getItemMeta().getDisplayName());
                p.closeInventory();
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Fly")){
                e.setCancelled(true);
                p.sendMessage("§a le report de :§r§c§l" + "§r§a pour : §r§c§l" + e.getCurrentItem().getItemMeta().getDisplayName() + "§r§a a bien était envoyé");
                p.closeInventory();
            }
            break;
        case REDSTONE:
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Particule")){
                e.setCancelled(true);
                p.closeInventory();
                Inventory invParticle = Bukkit.createInventory(null, 9, "§cParticules");
                
                
          	  	ItemStack itemReturn = new ItemStack(Material.BARRIER);
          	  	ItemMeta metaItemReturn = itemReturn.getItemMeta();
          	  	metaItemReturn.setDisplayName("§4§lRetournez au menu principale");
          	  	itemReturn.setItemMeta(metaItemReturn);
          	  	
          	  ItemStack itemParticle1 = new ItemStack(Material.BLAZE_POWDER);
        	  	ItemMeta metaItemPartice1 = itemParticle1.getItemMeta();
        	  	metaItemPartice1.setDisplayName("§cFlamme");
        	  	itemParticle1.setItemMeta(metaItemPartice1);
          	  	
          	  	
          	  	
          	  invParticle.setItem(8, itemReturn);
          	  invParticle.setItem(1, itemParticle1);
                p.openInventory(invParticle);
                p.updateInventory();
            }
            break;
        case BLAZE_POWDER:
        	 if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cFlamme")){
                 e.setCancelled(true);
                 PlayerManager.addParticleRedstone(p);
                 p.sendMessage("TEST");
        	 }
        	break;
        case BARRIER:
        	 if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lRetournez au menu principale")){
                 e.setCancelled(true);
                 p.closeInventory();
                 Inventory invCosm = Bukkit.createInventory(null, 18, "Cosmétiques");
           	  ItemStack partical = new ItemStack(Material.REDSTONE);
           	  ItemMeta metaPartical = partical.getItemMeta();
           	  metaPartical.setDisplayName("§6Particule");
           	  partical.setItemMeta(metaPartical);
           	  invCosm.setItem(3, partical);
                 p.openInventory(invCosm);
                 p.updateInventory();
                 
        	 }
        	break;
        default: break;
    }
}
}
