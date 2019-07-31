package bounen057.itemslotmachineplus.listener;

import bounen057.itemslotmachineplus.ItemSlotMachinePlus;
import bounen057.itemslotmachineplus.data.SlotOption;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.*;
import java.util.List;

public class UseSlotMachine implements Listener{
    private ItemSlotMachinePlus plugin;
    public UseSlotMachine(ItemSlotMachinePlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void OnUse(PlayerInteractEvent e){
        Player p = e.getPlayer();

        // 判定
        if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            return;
        }

        if(p.getInventory().getItemInMainHand().getType() != Material.GOLD_NUGGET){
            return;
        }

        if(e.getClickedBlock().getType() != Material.JUKEBOX){
            return;
        }

        // スロットの設定にあるかどうか
        Location target_l = e.getClickedBlock().getLocation();
        Boolean IsThere = false;
        String slotname = "";

        for(String name : new SlotOption(plugin).GetSlots()){

            for(int i = 1;i < 3;i++) {
                Location loc = new SlotOption(plugin).GetLocations(name, i);

                if (loc.getX() - 1 <= target_l.getX() && loc.getX() + 1 >= target_l.getX()) {
                    if (loc.getZ() - 1 <= target_l.getZ() && loc.getZ() + 1 >= target_l.getZ()) {
                        IsThere = true;
                        slotname = name;
                    }
                }

            }

        }

        if(!IsThere){
            return;
        }

        if(p.getInventory().getItemInMainHand().getAmount() < new SlotOption(plugin).getCoinAmount(slotname)){
            return;
        }

        // スロットを回す処理
        new SlotOption(plugin).addNowStock(slotname);
    }

}
