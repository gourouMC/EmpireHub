package fr.gourou;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.gourou.manager.PlayerManager;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class Commands implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		Player player = (Player) p;
		if(label.equalsIgnoreCase("test")){
			p.sendMessage("§4[§3SERVER§4] §1gg");
		}
		
		if(label.equalsIgnoreCase("report")) {
			if(args.length == 0) {
				p.sendMessage("§4[§3SERVER§4] §cCommande invalide (entre un joueur) : /report <joueur> <raison>");
				return false;
			}
			if(args.length == 1) {
				p.sendMessage("§4[§3SERVER§4] §cCommande invalide (entre une raison) : /report <joueur> <raison>");
				return false;
			}
			if(args.length >= 2) {
				String raison = "";
				for(int i = 1; i < args.length; i++) {
					raison += args[i] + " ";
				}
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage("§4[§3SERVER§4]§r §cjoueur invalide");
					return false;
				}
				String targetString = Bukkit.getPlayer(args[0]).getName(); 
				for(Player all: Bukkit.getOnlinePlayers()) {
					if(all.isOp()) {
						all.sendMessage("§4[§3SERVER§4]§r §3" +  player.getName() + "§c a report §4" + targetString + "§c pour : §r" + raison);
					}
					p.sendMessage("§4[§3SERVER§4] Rapport envoyé");
				}
				
			}
			}
		if(label.equalsIgnoreCase("reportTest")) {
			if(args.length == 0) {
				p.sendMessage("§4[§3SERVER§4] §cCommande invalide (entre un joueur) : /report <joueur>");
				return false;
			}
			String targetString = Bukkit.getPlayer(args[0]).getName(); 
			 Inventory invReport = Bukkit.createInventory(null, 18, "§9Report: §r§l§c" +  targetString);
			 
			 
          	  ItemStack fly = new ItemStack(Material.FEATHER);
          	  ItemMeta metaFly = fly.getItemMeta();
          	  metaFly.setDisplayName("§3Fly");
          	  fly.setItemMeta(metaFly);
          	  
          	  
          	  	invReport.setItem(0, fly);
          	  
                player.openInventory(invReport);
                player.updateInventory();
                
		}
		if(label.equalsIgnoreCase("vanish")) {
			if(PlayerManager.vanish.contains(p)) {
				PlayerManager.removeVanish(player);
			}else {
				PlayerManager.addVanish(player);
			}
		}
		if(label.equalsIgnoreCase("spawn")) {
			player.teleport(new Location(player.getWorld(),5.45 ,171 ,6.45));
		p.sendMessage("§4[§3SERVER§4]§r §2Vous venez d'être envoyé au spawn");	
		 player.playSound(player.getLocation(), Sound.CHEST_OPEN, 100f, 1f);
		}
		
		if(label.equalsIgnoreCase("ping")) {
			if(p instanceof Player){
				if(args.length >= 2) {
					p.sendMessage("§4[§3SERVER§4]§r §cTrop d'arguments");
					return false;
				}
				if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]); 
					EntityPlayer nmsPlayer = ((CraftPlayer) target).getHandle();
					if(nmsPlayer.ping <= 0) {
						p.sendMessage("§4[§3SERVER§4]§r §cVeuillez réessayer plus tard ping invalide");
						return false;
					}
					p.sendMessage("§4[§3SERVER§4]§r §eLe ping de : §l" + target.getName() + "§r§e est de : §r§6" + nmsPlayer.ping);
					return true;
				}
                EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
				if(nmsPlayer.ping <= 0) {
					p.sendMessage("§4[§3SERVER§4]§r §cVeuillez réessayer plus tard ping invalide");
					return false;
				}
                player.sendMessage("§4[§3SERVER§4]§r §eVotre ping est de : §6" + nmsPlayer.ping);
            }
		}
		
		if(label.equalsIgnoreCase("removeSpawn")) {
			if(!p.isOp()) {
				p.sendMessage("§4[§3SERVER§4]§r §cVous n'avez pas la permission");
				return false;
			}
			if(p instanceof Player) {
				if(args.length == 0) {
					p.sendMessage("§4[§3SERVER§4]§r §1Veuillez entré le nom d'un joueur");
					return false;
				}
				try {
					Player target = Bukkit.getPlayer(args[0]); 
					if(target == null) {
						p.sendMessage("§4[§3SERVER§4]§r §cJoueur invalide");
						return false;
					}
					PlayerManager.removeSpawn(target);
					 player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 100f, 1f);
					p.sendMessage("§4[§3SERVER§4] §r§c" + target + " a été retiré avec succés de la liste spawn");
				}catch (Exception e) {
				p.sendMessage("§1Joueur invalide ou Déconnecté");
				return false;
				}
			}
		}
		if(label.equalsIgnoreCase("watchspawn")) {
			if(!p.isOp()) {
				p.sendMessage("§4[§3SERVER§4]§r §cVous n'avez pas la permission");
				return false;
			}
			if(PlayerManager.spawn.isEmpty()) {
				p.sendMessage("§4[§3SERVER§4]§r §c Aucun joueur dans cette liste");
				return false;
				}
			p.sendMessage("§4[§3SERVER§4]§r §cIl y a : §l§1" + PlayerManager.spawn.size() + "§r§c joueur dans cette liste");
		}
		
		if(label.equalsIgnoreCase("addspawn")) {
			if(!p.isOp()) {
				p.sendMessage("§4[§3SERVER§4]§r §cVous n'avez pas la permission");
				return false;
			}
			if(p instanceof Player) {
				if(args.length == 0) {
					p.sendMessage("§4[§3SERVER§4]§r §1Veuillez entré le nom d'un joueur");
					return false;
				}
				try {
					Player target = Bukkit.getPlayer(args[0]); 
					if(target == null) {
						p.sendMessage("§4[§3SERVER§4]§r §cJoueur invalide");
						return false;
					}
					PlayerManager.addSpawn(target);
					 player.playSound(player.getLocation(), Sound.ANVIL_USE, 100f, 1f);
					p.sendMessage("§4[§3SERVER§4]§r §2" + target + " a été ajouté avec succés de la liste spawn");
				}catch (Exception e) {
				p.sendMessage("§4[§3SERVER§4]§r §1Joueur invalide ou Déconnecté");
				return false;
				}
			}
		}
		if(label.equalsIgnoreCase("mute")){
			if(!p.isOp()) {
				p.sendMessage("§c Vous n'avez pas la permissions");
				return false;
			}
			if(args.length == 0) {
				p.sendMessage("§c Veuillez entrer le nom d'un joueur");
				return false;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(PlayerManager.mute.contains(target)) {
				p.sendMessage("§cjoueur déjà mute");
				return false;
			}
			if(target == null) {
				p.sendMessage("§cJoueur invalide");
				return false;
			}
				PlayerManager.addMute(target);
				p.sendMessage("§cMute réussi");
		}
		if(label.equalsIgnoreCase("unmute")){
			if(!p.isOp()) {
				p.sendMessage("§c Vous n'avez pas la permissions");
				return false;
			}
			if(args.length == 0) {
				p.sendMessage("§c Veuillez entrer le nom d'un joueur");
				return false;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {
				p.sendMessage("§cJoueur invalide");
				return false;
			}
			if(!PlayerManager.mute.contains(target)) {
				p.sendMessage("§cLe joueur :§l" + target +"§r§c n'est pas mute");
				return false;
			}
				PlayerManager.removeMute(target);
				p.sendMessage("§cUnMute réussi");
		}
		if(label.equalsIgnoreCase("addbandword")) {
			if(p instanceof Player) {
				if(args.length == 0) {
					p.sendMessage("§4[§3SERVER§4]§r §1Veuillez entré le nom d'un joueur");
					return false;
				}
				try {
					Player target = Bukkit.getPlayer(args[0]); 
					if(target == null) {
						p.sendMessage("§4[§3SERVER§4]§r §cJoueur invalide");
						return false;
					}
					PlayerManager.banWordAccpet.add(target);
					p.sendMessage("§4[§3SERVER§4]§r §2" + target + " a été ajouté avec succés de la liste BanWord");
				}catch (Exception e) {
				p.sendMessage("§4[§3SERVER§4]§r §1Joueur invalide ou Déconnecté");
				return false;
				}
			}
		}
		if(label.equalsIgnoreCase("removebanword")) {
			if(p instanceof Player) {
				if(args.length == 0) {
					p.sendMessage("§4[§3SERVER§4]§r §1Veuillez entré le nom d'un joueur");
					return false;
				}
				try {
					Player target = Bukkit.getPlayer(args[0]); 
					if(target == null) {
						p.sendMessage("§4[§3SERVER§4]§r §cJoueur invalide");
						return false;
					}
					PlayerManager.banWordAccpet.remove(target);
					p.sendMessage("§4[§3SERVER§4]§r §2" + target + " a été ajouté avec succés de la liste BanWord");
				}catch (Exception e) {
				p.sendMessage("§4[§3SERVER§4]§r §1Joueur invalide ou Déconnecté");
				return false;
				}
			}
		}
		if(label.equalsIgnoreCase("watchbanword")) {
			if(!p.isOp()) {
				p.sendMessage("§4[§3SERVER§4]§r §cVous n'avez pas la permission");
				return false;
			}
			if(PlayerManager.banWordAccpet.isEmpty()) {
				p.sendMessage("§4[§3SERVER§4]§r §c Aucun joueur dans cette liste");
				return false;
				}
			p.sendMessage("§4[§3SERVER§4]§r §cIl y a : §l§1" + PlayerManager.banWordAccpet.size() + "§r§c joueur dans cette liste");
		}
		if(label.equalsIgnoreCase("banword")) {
			if(args.length == 0) {
				p.sendMessage("§cVeuillez entrer une valeur : /banword <accept/deny>");
				return false;
			}
			if(args[0].equalsIgnoreCase("accept")) {
				if(PlayerManager.banWordAccpet.contains(p)) {
					p.sendMessage("§cVous accepté déjà les mots interdits");
					return false;
				}
				PlayerManager.banWordAccpet.add((Player) p);
				p.sendMessage("§2Vous acceptez désormais les mots interdits.");
				return true;
			}
			if(args[0].equalsIgnoreCase("deny")) {
				if(!PlayerManager.banWordAccpet.contains(p)) {
					p.sendMessage("§cVous n'accepté déjà pas les mots interdits");
					return false;
				}
				PlayerManager.banWordAccpet.remove(p);
				p.sendMessage("Vous n'acceptez plus les mots interdits");
				return true;
			}
		}
		return false;
	}

}
