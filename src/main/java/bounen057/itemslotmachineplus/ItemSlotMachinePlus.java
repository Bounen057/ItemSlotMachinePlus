package bounen057.itemslotmachineplus;

import bounen057.itemslotmachineplus.command.GetSlotPlus;
import bounen057.itemslotmachineplus.data.CustomConfig;
import bounen057.itemslotmachineplus.listener.UseSlotMachine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemSlotMachinePlus extends JavaPlugin {


    public String logo = "§8[§eItemSlotMachine+§8]";
    public CustomConfig config,slotoption;

    @Override
    public void onEnable() {
        config = new CustomConfig(this);
        config.saveDefaultConfig();

        slotoption = new CustomConfig(this,"slotoption.yml");
        slotoption.saveDefaultConfig();

        Bukkit.getPluginCommand("slotplus").setExecutor(new GetSlotPlus(this));

        Bukkit.getPluginManager().registerEvents(new UseSlotMachine(this),this);
    }

    @Override
    public void onDisable() {
    }


    public void show(Player p){
        new VaultManager(this).showBalance(p.getUniqueId());
    }
    public double get(Player p){
        return new VaultManager(this).getBalance(p.getUniqueId());
    }
    public void withraw(Player p,Double money){
        new VaultManager(this).withdraw(p.getUniqueId(),money);
    }
    public void deposit(Player p, Double money){
        new VaultManager(this).deposit(p.getUniqueId(),money);
    }
}
