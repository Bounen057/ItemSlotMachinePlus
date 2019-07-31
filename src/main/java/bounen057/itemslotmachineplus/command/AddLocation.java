package bounen057.itemslotmachineplus.command;

import bounen057.itemslotmachineplus.ItemSlotMachinePlus;
import bounen057.itemslotmachineplus.data.SlotOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AddLocation {
    private ItemSlotMachinePlus plugin;
    public AddLocation(ItemSlotMachinePlus plugin) {
        this.plugin = plugin;
    }

    public void add(Player p,String[] args){
        Location ploc = p.getLocation();
        Location addloc = new Location(ploc.getWorld(),(int)ploc.getX(),(int)ploc.getY() - 1,(int)ploc.getZ());

        int num = -1;
        try {
            num = Integer.parseInt(args[2]);
        }catch (Exception e){
            p.sendMessage(plugin.logo+"§4正常な値を入力してください");
            return;
        }

        if(!(num == 1 || num == 2)){
            return;
        }


        new SlotOption(plugin).AddLocations(p,addloc,args[1],Integer.parseInt(args[2]));

        p.sendMessage(plugin.logo+"§a登録しました!　"+addloc);
    }
}
