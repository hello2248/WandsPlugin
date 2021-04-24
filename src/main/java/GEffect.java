import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;

public class GEffect {
    
    void makeParticle(Player player, Location loc, float r, float g, float b){
        PacketContainer p = Gallery.manager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        p.getModifier().writeDefaults();
        p.getParticles().write(0, EnumWrappers.Particle.REDSTONE);
        p.getFloat().write(0, (float)loc.getX()).write(1, (float)loc.getY()).write(2,
                (float)loc.getZ()).write(3, r).write(4, g).write(5, b).write(6, 1f);
        try{
            Gallery.manager.sendServerPacket(player, p);
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    void makeParticle(Player player, Location loc){
        PacketContainer p = Gallery.manager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        p.getModifier().writeDefaults();
        p.getParticles().write(0, EnumWrappers.Particle.FLAME);
        p.getFloat().write(0, (float)loc.getX()).write(1, (float)loc.getY()).write(2,
                (float)loc.getZ());
        try{
            Gallery.manager.sendServerPacket(player, p);
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
    
    Location transCoords(Location origin, Vector xAxis, Vector yAxis, Vector zAxis,
                                double x, double y, double z){
        return origin.clone().add(xAxis.clone().multiply(x)).add(yAxis.clone().multiply(y)).add(zAxis.clone().multiply(z));
    }
    
    Location transCoords(Location origin, Vector xAxis, Vector yAxis, Vector zAxis, Vector v){
        return origin.clone().add(xAxis.clone().multiply(v.getX()))
                .add(yAxis.clone().multiply(v.getY())).add(zAxis.clone().multiply(v.getZ()));
    }
    
}
