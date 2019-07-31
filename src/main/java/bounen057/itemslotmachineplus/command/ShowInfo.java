package bounen057.itemslotmachineplus.command;

import bounen057.itemslotmachineplus.ItemSlotMachinePlus;
import bounen057.itemslotmachineplus.data.SlotOption;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ShowInfo {
    private ItemSlotMachinePlus plugin;
    public ShowInfo(ItemSlotMachinePlus plugin) {
        this.plugin = plugin;
    }

    public void show(Player p,String name){
        SlotOption so = new SlotOption(plugin);
        if(!so.CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのような名前のスロットは存在しません");
        }

        // 変数
        Location l_1 = so.GetLocations(name,1);
        Location l_2 = so.GetLocations(name,2);
        int coin = so.getCoinAmount(name);
        Double playstock = so.getOnePlayStock(name);
        Double startstock = so.getStartStock(name);
        Double stock = so.getNowStock(name);
        String message = so.getMessage(name);


        p.sendMessage("§9§l-=-=- §6§l"+name+"§9§l stats -=-=-");

        if(l_1 != null) {
            p.sendMessage("§blocation1 : §7"+l_1.getX()+"/"+l_1.getY()+"/"+l_1.getZ());
        }else{
            p.sendMessage("§blocation1 : §4nothing");
        }

        if(l_2 != null) {
            p.sendMessage("§blocation2 : §7"+l_2.getX()+"/"+l_2.getY()+"/"+l_2.getZ());
        }else{
            p.sendMessage("§blocation2 : §4nothing");
        }

        if(coin != 0){
            p.sendMessage("§bcoin : §7"+coin);
        }else{
            p.sendMessage("§bcoin : §4nothing");
        }

        if(playstock != 0){
            p.sendMessage("§b1回のストック : §7"+playstock);
        }else{
            p.sendMessage("§b1回のストック : §4nothing");
        }

        if(startstock != 0){
            p.sendMessage("§b初期当選金額 : §7"+startstock);
        }else {
            p.sendMessage("§b初期当選金額 : §4noting");
        }

        if(message != null){
            p.sendMessage("§b当選時のメッセージ : §7"+message);
        }else{
            p.sendMessage("§b当選時のメッセージ : §4nothing");
        }


        p.sendMessage("");
        p.sendMessage("§6現在の当たり金額 : §7"+stock);
    }

}
