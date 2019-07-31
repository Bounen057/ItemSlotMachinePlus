package bounen057.itemslotmachineplus.command;

import bounen057.itemslotmachineplus.ItemSlotMachinePlus;
import bounen057.itemslotmachineplus.data.SlotOption;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSlotPlus implements CommandExecutor {
    private ItemSlotMachinePlus plugin;
    public GetSlotPlus(ItemSlotMachinePlus plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(args.length == 0 || args[0].equals("help")){
            help(p);
            return false;
        }

        if(args[0].contains("info")){
            if(args.length == 2){
                new ShowInfo(plugin).show(p,args[1]);
            }else{
                return false;
            }
        }

        if(args[0].contains("list")){
            p.sendMessage("§6§l-=- §9§lSlot List §6§l-=-");
            for(String name : new SlotOption(plugin).GetSlots()){
                p.sendMessage("§9≫ §7-"+name);
            }
            return false;
        }

        if(args[0].contains("coin")){
            if(args.length == 3) {
                new SlotOption(plugin).setCoinAmount(p, args[1], args[2]);
            }else{
                p.sendMessage(plugin.logo+"§4失敗しました");
                return false;
            }
        }

        if(args[0].contains("playstock")){
            if(args.length == 3) {
                new SlotOption(plugin).setOnePlayStock(p, args[1], args[2]);
            }else{
                p.sendMessage(plugin.logo+"§4失敗しました");
                return false;
            }
        }

        if(args[0].contains("startstock")){
            if(args.length == 3) {
                new SlotOption(plugin).setStartStock(p, args[1], args[2]);
            }else{
                p.sendMessage(plugin.logo+"§4失敗しました");
                return false;
            }
        }

        if(args[0].contains("message")){
            if(args.length == 3) {
                new SlotOption(plugin).setMessage(p, args[1], args[2]);
            }else{
                p.sendMessage(plugin.logo+"§4失敗しました");
                return false;
            }
        }

        if(args[0].contains("add")){
            if(args.length == 2) {
                new SlotOption(plugin).AddSlot(args[1]);
                p.sendMessage(plugin.logo+"§a成功しました!");
            }else{
                p.sendMessage(plugin.logo+"§4失敗しました");
                return false;
            }
        }

        // 座標を追加
        if(args[0].contains("location")){
            if(args.length == 3) {
                new AddLocation(plugin).add(p, args);
            }else{
                return false;
            }
        }



        if(args[0].contains("hit")){
            if(args.length == 3) {
                new SlotOption(plugin).hit(args[1],args[2]);
            }else{
                return false;
            }
        }

        return false;
    }

    private void help(Player p){
        p.sendMessage("§6§l-=-  "+plugin.logo+"  §6§l-=-");
        p.sendMessage("§7|§9/slotplus help §7-helpを表示します");
        p.sendMessage("§7|§9/slotplus list §7-スロット一覧");
        p.sendMessage("§7|§9/slotplus info <name> §7-スロットの情報を見る");
        p.sendMessage("");
        p.sendMessage("§7|§9/slotplus add <name> §7-スロットの設定を追加");
        p.sendMessage("§7|§9/slotplus coin <name> <amount> §7-回すのに必要なコインの設定(通常版と合わせて)");
        p.sendMessage("§7|§9/slotplus playstock <name> <amount> §7-一回ごとに加算されるストックの設定");
        p.sendMessage("§7|§9/slotplus startstock <name> <amount> §7-初期当選金額");
        p.sendMessage("§7|§9/slotplus message <name> <message> §7-当選時のメッセージの設定");
        p.sendMessage("§7|§9/slotplus location <name> <1/2> §7-JukeBoxを座標(1つ下のブロック)追加");
        p.sendMessage("");
        p.sendMessage("§7|§9/slotplus hit <slotname> <playername> §7-当選させる");
    }
}
