package fr.gourou.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class PlayerManager implements Listener{
	private static Player player;
    public PlayerManager(Player player){
        this.player = player;
    }
	public static ArrayList<Player> spawn = new ArrayList<>();
	public static ArrayList<Player> IsOp = new ArrayList<>();
	public static ArrayList<Player> mute = new ArrayList<>();
	public static ArrayList<Player> banWordAccpet = new ArrayList<>();
	public static ArrayList<Player> ParticleRedstone = new ArrayList<>();
	public static ArrayList<Player> vanish = new ArrayList<>();
	public static ArrayList<Player> getSpawn() {
		return spawn;
	}
	public static void setSpawn(Player p) {
		spawn.add(p);
	}
	public static void removeSpawn(Player p) {
		spawn.remove(p);
	}
	public static void addSpawn(Player p) {
		spawn.add(p);
	}
	public static boolean IsOp() {
		for(Player all: Bukkit.getOnlinePlayers()) {
			if(all.isOp()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	public static void addMute(Player p) {
		p.sendMessage("§cVous venez d'etre mute");
		p.playSound(p.getLocation(), Sound.ENDERMAN_SCREAM, 100f, 1f);
		mute.add(p);
	}
	public static void removeMute(Player p) {
		 p.playSound(p.getLocation(), Sound.NOTE_PIANO, 100f, 1f);
		p.sendMessage("§cVous venez d'etre Unmute");
		mute.remove(p);
	}
	
	 public static void drawCircle(Location loc, float radius){
	        for(double t = 0; t<50; t+=0.5) {
	            float x = radius*(float)Math.sin(t);
	            float z = radius*(float)Math.cos(t);
	            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,(float) loc.getX() + x, (float) loc.getY(),(float) loc.getZ() + z, 0, 0, 0, 0, 1);
	            for(Player online : Bukkit.getOnlinePlayers()){
	                ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
	            }
	        }
	    }
	 
	    public static void drawTornado(Location loc, float radius,float increase){
	        float y = (float) loc.getY();
	        for(double t = 0; t<50; t+=0.05){
	            float x = radius*(float)Math.sin(t);
	            float z = radius*(float)Math.cos(t);
	            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,(float) loc.getX() + x, y,(float) loc.getZ() + z, 0, 0, 0, 0, 1);
	            for(Player online : Bukkit.getOnlinePlayers()){
	                ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
	            }
	            y += 0.01f;
	            radius += increase;
	        
	        }
	    }
	
	public static void PlayParticle(Player p) {
		drawCircle(p.getLocation(), 2);
		drawTornado(p.getLocation(), 2, 10);
	}
	    
	
	public static void addParticleRedstone(Player p) {
		
		if(ParticleRedstone.contains(p)) {
			p.sendMessage("Vous avez des déjà ces particules");
			return;
		}
		
		ParticleRedstone.add(p);
		PlayParticle(p);
		p.sendMessage("§cDésormais des particules de flamme seront derrière vous");
	}
	public static void addVanish(Player p) {
		vanish.add(p);
		if(!p.isOp()) {
			p.sendMessage("§cVous n'avez pas la permission de vous mettre en vanish");
			return;
		}
        if(vanish.contains(p)){
            Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
        } else {
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
        }
		p.setAllowFlight(true);
		p.setFlying(true);
		p.getInventory().clear();
		p.sendMessage("§a§lVous êtes désormais en vanish");
		p.playSound(p.getLocation(), Sound.WATER, 100f, 1f);
	}
	public static void removeVanish(Player p) {
		Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
		p.sendMessage("§c§lVous n'êtes plus en vanish");
		p.playSound(p.getLocation(), Sound.DONKEY_DEATH, 100f, 1f);
	}

}
