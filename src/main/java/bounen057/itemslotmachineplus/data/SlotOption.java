package bounen057.itemslotmachineplus.data;

import bounen057.itemslotmachineplus.ItemSlotMachinePlus;
import bounen057.itemslotmachineplus.command.GetSlotPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SlotOption {
    private ItemSlotMachinePlus plugin;
    public SlotOption(ItemSlotMachinePlus plugin) {
        this.plugin = plugin;
    }

    // slotplusの追加
    public void AddSlot(String name){
        List<String> names = GetSlots();
        if(names == null){
            names = new ArrayList<>();
        }
        names.add(name);

        plugin.slotoption.getConfig().set("slots",names);
        plugin.slotoption.saveConfig();
    }

    // スロットの一覧を入手
    public List<String> GetSlots(){
        return plugin.slotoption.getConfig().getStringList("slots");
    }

    // スロットが存在するか確認
    public Boolean CheckSlot(String name){
        if(GetSlots().contains(name)){
            return true;
        }
        return false;
    }


    // slotの座標の追加
    public void AddLocations(Player p,Location l, String name, int num){
        if(!CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのスロットは存在しません!");
            return;
        }

        plugin.slotoption.getConfig().set("slot."+name+".locations."+num,l);
        plugin.slotoption.saveConfig();
    }

    // slotのJukeBoxの座標の記憶
    //
    // slot:
    //   <name>:
    //     locations:
    //       1:
    //       2:
    //
    public Location GetLocations(String name,int num){
        Location locations = (Location) plugin.slotoption.getConfig().get("slot."+name+".locations."+num);
        return locations;
    }

    // コインの数の設定
    public void setCoinAmount(Player p,String name,String amount){
        if(!CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのスロットは存在しません!");
            return;
        }

        try {
            plugin.slotoption.getConfig().set("slot." + name + ".coin", Integer.parseInt(amount));
            p.sendMessage(plugin.logo+"§a設定できました!");
        }catch (Exception e){
            p.sendMessage(plugin.logo+"§4数値を入力してください!");
        }

        plugin.slotoption.saveConfig();
    }

    // コインの取得
    public int getCoinAmount(String name){
        return plugin.slotoption.getConfig().getInt("slot."+name+".coin");
    }

    // 一回のストックの設定
    public void setOnePlayStock(Player p,String name,String amount){
        if(!CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのスロットは存在しません!");
            return;
        }

        try {
            plugin.slotoption.getConfig().set("slot." + name + ".oneplaystock", Double.valueOf(amount));
            p.sendMessage(plugin.logo+"§a設定できました!");
        }catch (Exception e){
            p.sendMessage(plugin.logo+"§4数値を入力してください!");
        }

        plugin.slotoption.saveConfig();
    }

    // 一回のストックの取得
    public Double getOnePlayStock(String name){
        return plugin.slotoption.getConfig().getDouble("slot."+name+".oneplaystock");
    }

    // 初期当たり金額の設定
    public void setStartStock(Player p,String name,String amount){
        if(!CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのスロットは存在しません!");
            return;
        }

        try {
            plugin.slotoption.getConfig().set("slot." + name + ".startstock", Double.valueOf(amount));
            p.sendMessage(plugin.logo+"§a設定できました!");
        }catch (Exception e){
            p.sendMessage(plugin.logo+"§4数値を入力してください!");
        }

        plugin.slotoption.saveConfig();
    }

    // 初期当たり金額の取得
    public Double getStartStock(String name){
        return plugin.slotoption.getConfig().getDouble("slot."+name+".startstock");
    }

    // メッセージの設定
    public void setMessage(Player p,String name,String message){
        if(!CheckSlot(name)){
            p.sendMessage(plugin.logo+"§4そのスロットは存在しません!");
            return;
        }

        message = message.replaceAll("&","§");

        plugin.slotoption.getConfig().set("slot." + name + ".message", message);
        p.sendMessage(plugin.logo+"§a設定できました!");

        plugin.slotoption.saveConfig();
    }

    // メッセージの取得
    public String getMessage(String name){
        return plugin.slotoption.getConfig().getString("slot."+name+".message");
    }


    // 一回分当たり金額を足す
    public void addNowStock(String name){
        Double amount = getNowStock(name);
        if(amount == 0){
            amount = getStartStock(name);
        }

        amount += getOnePlayStock(name);
        plugin.slotoption.getConfig().set("slot."+name+".stock",amount);
        plugin.slotoption.saveConfig();
    }

    // 現在の当たり金額の取得
    public Double getNowStock(String name){
        return plugin.slotoption.getConfig().getDouble("slot."+name+".stock");
    }


    // 当たった時の処理
    public void hit(String slotname,String playername){
        Player p = Bukkit.getPlayer(playername);
        plugin.deposit(p,getNowStock(slotname));

        // message関係
        String message = getMessage(slotname);

        message = message.replace("<money>",getNowStock(slotname).toString());
        message = message.replace("<player>",playername);

        Bukkit.broadcastMessage(message);

        plugin.slotoption.getConfig().set("slot."+slotname+".stock",getStartStock(slotname));
        plugin.slotoption.saveConfig();
    }
}
