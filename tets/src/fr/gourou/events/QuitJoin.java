package fr.gourou.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import fr.gourou.manager.PlayerManager;
import fr.gourou.tools.Title;
public class QuitJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		PlayerManager.setSpawn(p);
		if(PlayerManager.spawn.contains(p)) {
			p.sendMessage("§cVous etes bien dans la liste 'spawn'");
		}
		p.getInventory().clear();
		  p.playSound(p.getLocation(), Sound.ORB_PICKUP, 100f, 1f);
	    Title title = new Title("§1§lBienvenue sur", "§9Empire ac");
	    title.send(p, 0, 4, 1);
		event.setJoinMessage("§9[§2+§9] §f§l" + p.getName());
		ItemStack nav = new ItemStack(Material.COMPASS);
		nav.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
		ItemMeta metaNav = nav.getItemMeta();
		metaNav.setDisplayName("§1Navigateur");
		nav.setItemMeta(metaNav);
		p.getInventory().setItem(0, nav);
		ItemStack cosm = new ItemStack(Material.EMERALD);
		ItemMeta metaCosm = cosm.getItemMeta();
		metaCosm.setDisplayName("§2§lCosmétiques");
		cosm.setItemMeta(metaCosm);
		p.getInventory().setItem(4, cosm);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		event.setQuitMessage("§0[§4-§0]§l§c" + p.getName());
	}
}
