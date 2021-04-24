import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InvClickEvent implements Listener {
    
    Gallery p;
    
    public InvClickEvent(Gallery p){
        this.p = p;
    }
    
    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if(e.getCurrentItem() == null)
            return;
        if(!ChatColor.stripColor(e.getInventory().getTitle()).equals("Choose a Wand"))
            return;
        e.setCancelled(true);
        e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
        new BukkitRunnable(){
            public void run(){
                e.getWhoClicked().closeInventory();
            }
        }.runTaskLater(p, 1);
    }
    
}
