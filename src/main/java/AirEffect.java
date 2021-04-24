import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AirEffect extends GEffect{
    
    final float FORCE = 2;
    final float RADIUS = 0.5f;
    final float VELOCITY = 1f;
    final int DISTANCE = 10;
    float t;
    
    AirEffect(Location loc, Player player, Gallery p){
        loc.add(0, 1.5, 0).add(loc.getDirection().multiply(1.1));
        t = 1.1f;
        if(loc.getYaw() < 0)
            loc.setYaw(360 + loc.getYaw());
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
                loc.getWorld().getNearbyEntities(loc//.add(loc.getDirection().multiply(VELOCITY/20f)),
                        ,RADIUS, RADIUS, RADIUS).forEach(k -> {
                    if(!k.equals(player))
                        k.setVelocity(parallel.clone().multiply(FORCE));
                });
                loc.add(parallel.clone().multiply(0.1)).add(loc.getDirection().multiply(0.2));
                loc.setPitch(loc.getPitch() - 20);
                
                makeParticle(player, loc,0.6588f, 0.6588f, 0.6588f);
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, 0, RADIUS), 0.6588f, 0.6588f, 0.6588f);
                makeParticle(player, transCoords(loc, parallel, vertical, normal, 0, 0, -RADIUS), 0.6588f, 0.6588f, 0.6588f);
                if(DISTANCE - t <= 0.1)
                    this.cancel();
            }
        }.runTaskTimer(p, 0, 1);
    }
    
}
