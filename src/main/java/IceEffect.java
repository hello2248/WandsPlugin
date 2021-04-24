import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class IceEffect extends GEffect{
    
    final float RADIUS = 1.3f;
    final float VELOCITY = 2f;
    final int DISTANCE = 10;
    float t;
    
    IceEffect(Location loc, Player player, Gallery p){
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
                    if(k instanceof Creature){
                        ((Creature) k).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
                    }
                });
                for(int i = 0; i < 6; i++){
                    Vector v1 = new Vector(0, Math.cos(i*Math.PI/3 + Math.floor(t/1.5)*Math.PI/6), Math.sin(i*Math.PI/3 + Math.floor(t/1.5)*Math.PI/6)).multiply(RADIUS);
                    Vector v2 = new Vector(0, Math.cos((i+1)*Math.PI/3 + Math.floor(t/1.5)*Math.PI/6), Math.sin((i+1)*Math.PI/3 + Math.floor(t/1.5)*Math.PI/6)).multiply(RADIUS);
                    makeParticle(player, transCoords(loc, parallel, vertical, normal, v1), 0.2901f, 0.8901f, 0.9882f);
                    makeParticle(player, transCoords(loc, parallel, vertical, normal, v1.add(v2.subtract(v1).multiply(1/3f))), 0.2901f, 0.8901f, 0.9882f);
                    makeParticle(player, transCoords(loc, parallel, vertical, normal, v1.add(v2)), 0.2901f, 0.8901f, 0.9882f);
                }
                if(DISTANCE - t <= 0.1)
                    this.cancel();
            }
        }.runTaskTimer(p, 0, 1);
    }
    
}
