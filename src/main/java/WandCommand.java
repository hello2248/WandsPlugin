import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WandCommand implements CommandExecutor {
    
    Inventory wandInventory;
    
    public WandCommand(Gallery p){
        wandInventory = p.getServer().createInventory(null, 27, "Choose a Wand");
        wandInventory.setItem(10, Gallery.fireWand);
        wandInventory.setItem(12, Gallery.iceWand);
        wandInventory.setItem(14, Gallery.airWand);
        wandInventory.setItem(16, Gallery.earthWand);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player))
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
        else
            ((Player) sender).openInventory(wandInventory);
        return true;
    }
}
