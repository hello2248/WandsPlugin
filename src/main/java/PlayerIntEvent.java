import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerIntEvent implements Listener {
    
    Gallery p;
    
    PlayerIntEvent(Gallery p){
        this.p = p;
    }
    
    @EventHandler
    public void onPlayerInt(PlayerInteractEvent e){
        if(e.getItem() == null)
            return;
        if(e.getItem().equals(Gallery.fireWand)){
            e.setCancelled(true);
            new FireEffect(e.getPlayer().getLocation(), e.getPlayer(), p);
        } else if(e.getItem().equals(Gallery.iceWand)){
            e.setCancelled(true);
            new IceEffect(e.getPlayer().getLocation(), e.getPlayer(), p);
        } else if(e.getItem().equals(Gallery.airWand)){
            e.setCancelled(true);
            new AirEffect(e.getPlayer().getLocation(), e.getPlayer(), p);
        }
    }
    
}
