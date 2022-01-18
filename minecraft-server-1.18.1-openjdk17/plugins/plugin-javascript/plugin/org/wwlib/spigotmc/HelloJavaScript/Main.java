package org.wwlib.spigotmc.HelloJavaScript;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngineFactory;
// imports for GraalJS bindings
import javax.script.Bindings;
import javax.script.ScriptContext;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

    private String NO_JAVASCRIPT_MESSAGE = "No JavaScript Engine available.";
    protected ScriptEngine engine = null;

    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        this.getLogger().severe("HelloJavaScript plugin enabled.");
        this.getLogger().severe("VM Details:");
        // this.getLogger().severe(org.graalvm.home.Version.getCurrent().toString());
        this.getLogger().severe(System.getProperty("java.vm.name"));
        this.getLogger().severe(System.getProperty("java.vm.version"));
        this.getLogger().severe(System.getProperty("java.vm.info"));

        Thread currentThread = Thread.currentThread();
        ClassLoader previousClassLoader = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(getClassLoader());
        try {
            this.getServer().getPluginManager().registerEvents(this, this);

            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            this.engine = scriptEngineManager.getEngineByName("JavaScript");
            if (this.engine == null) {
                this.getLogger().severe(NO_JAVASCRIPT_MESSAGE);
                this.getLogger().severe("Available engines include:");
                List<ScriptEngineFactory> engines = scriptEngineManager.getEngineFactories();
                for (ScriptEngineFactory f: engines) {
                    // System.out.println(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
                    this.getLogger().severe(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
                }
            } else {
                this.getLogger().severe("Found a JavaScript engine.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getLogger().severe(e.getMessage());
        } finally {
            currentThread.setContextClassLoader(previousClassLoader);
        }
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        Player player = evt.getPlayer(); // The player who joined
        PlayerInventory inventory = player.getInventory(); // The player's inventory
        ItemStack itemstack = new ItemStack(Material.DIAMOND, 64); // A stack of diamonds
        player.sendMessage("Welcome!");
        // if (!inventory.contains(itemstack)) {
            inventory.addItem(itemstack); // Adds a stack of diamonds to the player's inventory
            player.sendMessage("We gave you some diamonds!");
        // }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hellojs")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Hello JavaScript!");

                try {
                    // Bindings bindings = this.engine.createBindings();
                    // bindings.put("count", 3);
                    // bindings.put("name", "baeldung");
                    // String script = "var greeting='Hello ';" +
                    // "for(var i=count;i>0;i--) { " +
                    // "greeting+=name + ' '" +
                    // "}" +
                    // "greeting";
                    // Object bindingsResult = this.engine.eval(script, bindings);
                    // player.sendMessage(bindingsResult.toString());

                    String arg0 = args[0];
                    if (arg0.equalsIgnoreCase("cottage")) {
                        player.sendMessage("arg0 == cottage");
                        Location loc = player.getLocation();
                        loc.setY(loc.getY() + 5);
                        Block b = loc.getBlock();
                        b.setType(Material.DIAMOND_BLOCK);
                    } else if (arg0.equalsIgnoreCase("diamonds")) {
                        PlayerInventory inventory = player.getInventory(); // The player's inventory
                        ItemStack itemstackDiamonds = new ItemStack(Material.DIAMOND, 64); // A stack of diamonds
                        ItemStack itemstackWood = new ItemStack(Material.BIRCH_LOG, 64); // A stack of diamonds
                        player.sendMessage("Have some diamonds and wood!");
                        inventory.addItem(itemstackDiamonds);
                        inventory.addItem(itemstackWood);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    this.getLogger().severe(e.getMessage());
                }

                return true;
            }
        }
        return false;
    }

}