package org.wwlib.spigotmc.HelloWorld;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        //Fired when the server enables the plugin
        this.getLogger().severe("HelloWorld plugin enabled.");
        this.getLogger().severe("VM Details:");
        this.getLogger().severe(org.graalvm.home.Version.getCurrent().toString());
        this.getLogger().severe(System.getProperty("java.vm.name"));
        this.getLogger().severe(System.getProperty("java.vm.version"));
        this.getLogger().severe(System.getProperty("java.vm.info"));
        this.getLogger().severe("Available engines include:");
        List<ScriptEngineFactory> engines = (new ScriptEngineManager()).getEngineFactories();
        for (ScriptEngineFactory f: engines) {
            // System.out.println(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
            this.getLogger().severe(f.getLanguageName()+" "+f.getEngineName()+" "+f.getNames().toString());
        }
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hello")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Hello world!");
                return true;
            }
        }
        return false;
    }

}