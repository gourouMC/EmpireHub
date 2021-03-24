package fr.gourou.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.gourou.manager.PlayerManager;

public class PlayerCancel implements Listener{
	@EventHandler
	public void BreakBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(p.isOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void InteractAtEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if(p.isOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void InventoryInteract(InventoryClickEvent e) {
		if(e.getInventory().getTitle() == "Mini-jeux") {
			e.setCancelled(true);
		}else {
			if(PlayerManager.IsOp()){
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
			}
		}
		
	}
	@EventHandler
	public void PlaceBlock(BlockCanBuildEvent e) {
		if(PlayerManager.IsOp())return;
		e.setBuildable(false);
	}
	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void InteractOnEntity(EntityInteractEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void InteractAtEntity(PlayerInteractEntityEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void EntityDamageByEntty(EntityDamageByEntityEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void EntityDamge(EntityDamageEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void DropItem(PlayerDropItemEvent e) {
		if(PlayerManager.IsOp())return;
		e.setCancelled(true);
	}
	@EventHandler
	public void Food(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void CancelBcMute(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(!PlayerManager.mute.contains(p))return;
		for(Player all: Bukkit.getOnlinePlayers()) {
			if(all.isOp()) {
				return;
			}
		}
		e.setCancelled(true);
		p.sendMessage("§cVous etes mute");
	}
	}
	
