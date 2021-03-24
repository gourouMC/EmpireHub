package fr.gourou.manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener{
	
	String [] banWordStr = {"pute", "connard", "con", "salope", "fuck", "ez", "chienne"};
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
 
        if(e.getMessage().startsWith("$") && player.hasPermission("moderation.chat")){
 
            e.setCancelled(true);
 
            Bukkit.getOnlinePlayers().stream()
                    .filter(players -> players.hasPermission("moderation.chat"))
                    .forEach(players -> players.sendMessage("§7(§b§lSTAFF CHAT§7) §e" + player.getName() + "§f: §a" + e.getMessage().substring(1)));
        }

        if(e.getMessage().contains("pute")){
        	e.setCancelled(true);
        	player.sendMessage("§c§lTon message contient un mot interdit fait attention");
        	        	e.setCancelled(true);
        	            Bukkit.getOnlinePlayers().stream()
        	                    .filter(players -> PlayerManager.banWordAccpet.contains(player))
        	                    .forEach(players -> players.sendMessage("§c§l(§r§cBAN WORD CHAT§l) §r§e" + player.getName() + "§f: §a" + e.getMessage()));
        	            if(PlayerManager.banWordAccpet.isEmpty()) {
        	            	player.sendMessage("alone");
        	            	return;
        	            }
        	            return;
        }
        if(e.getMessage().contains("pute")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("con")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("connard")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("ez")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("salope")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("fortnite")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit -_-");
        }
        if(e.getMessage().contains("fuck")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
        if(e.getMessage().contains("mère")) {
        	e.setCancelled(true);
        	Player p = e.getPlayer();
        	p.sendMessage("§cVotre message contient un mot interdit");
        }
    }
}
