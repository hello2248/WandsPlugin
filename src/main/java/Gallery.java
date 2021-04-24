import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Gallery extends JavaPlugin implements Listener {
    
    public static ItemStack fireWand, iceWand, airWand, earthWand;
    public static ProtocolManager manager;
    
    @Override
    public void onEnable(){
        manager = ProtocolLibrary.getProtocolManager();
        
        fireWand = makeItem(Material.BLAZE_ROD, "&cFire Wand &7(Right Click)", new String[] {"&cA wand of fire"});
        iceWand = makeItem(Material.DIAMOND_AXE, "&bIce Wand &7(Right Click)", new String[] {"&bA wand of ice"});
        airWand = makeItem(Material.IRON_AXE, "&8Air Wand &7(Right Click)", new String[] {"&8A wand of air"});
        earthWand = makeItem(Material.BARRIER, "&2Earth Wand &7(Right Click)", new String[] {"&2A wand of earth"});
        
        getCommand("wands").setExecutor(new WandCommand(this));
        
        getServer().getPluginManager().registerEvents(new InvClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerIntEvent(this), this);
    }
    
    public ItemStack makeItem(Material m, String name, String[] lore){
        ItemStack i = new ItemStack(m);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(cString(name));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> l = new ArrayList<>();
        Arrays.stream(lore).forEach(k -> l.add(cString(k)));
        meta.setLore(l);
        i.setItemMeta(meta);
        return i;
    }
    
    String cString(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    
    
}
