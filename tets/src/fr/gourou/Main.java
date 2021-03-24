package fr.gourou;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.gourou.events.InventoryInteract;
import fr.gourou.events.ItemInteract;
import fr.gourou.events.PlayerCancel;
import fr.gourou.events.QuitJoin;
import fr.gourou.manager.ChatManager;
import fr.gourou.manager.PlayerManager;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Main extends JavaPlugin implements Listener{
	 public static Main plugin;
		@Override
		public void onEnable() {
			System.out.println("Le plugin empire a bien etait active");
			 plugin = this;
		        this.getServer().getPluginManager().registerEvents(this, this);
			getCommand("test").setExecutor(new Commands());
			getCommand("spawn").setExecutor(new Commands());
			getCommand("report").setExecutor(new Commands());
			getCommand("ping").setExecutor(new Commands());
			getCommand("removeSpawn").setExecutor(new Commands());
			getCommand("watchspawn").setExecutor(new Commands());
			getCommand("addspawn").setExecutor(new Commands());
			getCommand("mute").setExecutor(new Commands());
			getCommand("unmute").setExecutor(new Commands());
			getCommand("addbandword").setExecutor(new Commands());
			getCommand("removebanword").setExecutor(new Commands());
			getCommand("watchbanword").setExecutor(new Commands());
			getCommand("banword").setExecutor(new Commands());
			getCommand("reporttest").setExecutor(new Commands());
			getCommand("vanish").setExecutor(new Commands());
			
			Bukkit.getPluginManager().registerEvents(this, this);
			Bukkit.getPluginManager().registerEvents(new QuitJoin(), this);
			Bukkit.getPluginManager().registerEvents(new ItemInteract(), this);
			Bukkit.getPluginManager().registerEvents(new PlayerManager(null), this);
			Bukkit.getPluginManager().registerEvents(new PlayerCancel(), this);
			Bukkit.getPluginManager().registerEvents(new InventoryInteract(), this);
			Bukkit.getPluginManager().registerEvents(new ChatManager(), this);
			
			

	        /*for (Entity entity : Bukkit.getWorld("world").getEntities()) {
	            if (entity instanceof Player) {
	                Player p= (Player) entity;

	                new BukkitRunnable() {

	                    @Override
	                    public void run() {
	                        float red = 0;
	                        float green =0;
	                        float blue = 255;
	                        Location location = p.getLocation();
	                        
	                        PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true, (float) location.getX(), (float) location.getY()+1, (float) location.getZ()-1, red, green, blue, (float)255, 0, 10);
	                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(particles);
	                        for (Player p : Bukkit.getOnlinePlayers()) {
	                			((CraftPlayer) p).getHandle().playerConnection.sendPacket(particles);
	                		}
	                    }
	                }.runTaskTimerAsynchronously(this, 200, 0);
	            }
	        }*/
		}
		
		
		
}