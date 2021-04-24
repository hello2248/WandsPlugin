import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class FireEffect extends GEffect{
    
    final float RADIUS = 0.3f;
    final float VELOCITY = 2f;
    final int DISTANCE = 10;
    float t;
    
    FireEffect(Location loc, Player player, Gallery p){
        loc.add(0, 1.5, 0).add(loc.getDirection().multiply(1.1));
        t = 1.1f;
        Location verticalLoc = loc.clone();
        float newPitch = loc.getPitch() - 90;
        if(newPitch < -90)
            newPitch += 180;
        verticalLoc.setPitch(newPitch);
        Vector parallel = loc.getDirection();
        Vector vertical = verticalLoc.getDirection();
        Vector normal = loc.getDirection().crossProduct(vertical);
        new BukkitRunnable(){
            public void run(){
                t += VELOCITY/20f;
                loc.getWorld().getNearbyEntities(loc.add(loc.getDirection().multiply(VELOCITY/20f)),
                        RADIUS, RADIUS, RADIUS).forEach(k -> {
                        if(!k.equals(player))
                            k.setFireTicks(60);
                });
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, Math.sin(t*4), Math.cos(t*4)));
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, -Math.sin(t*4), -Math.cos(t*4)));
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, -Math.cos(t*4), -Math.sin(t*4)));
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, Math.cos(t*4), Math.sin(t*4)));
                if(DISTANCE - t <= 0.1)
                    this.cancel();
            }
        }.runTaskTimer(p, 0, 1);
    }
    
}
