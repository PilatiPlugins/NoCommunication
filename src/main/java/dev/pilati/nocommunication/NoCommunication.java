package dev.pilati.nocommunication;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import dev.pilati.nocommunication.listener.Book;
import dev.pilati.nocommunication.listener.ChatCommand;
import dev.pilati.nocommunication.listener.ChatMessage;
import dev.pilati.nocommunication.listener.ItemRename;
import dev.pilati.nocommunication.listener.Sign;
import dev.pilati.nocommunication.manager.MessageManager;
import dev.pilati.nocommunication.manager.PermissionManager;

public class NoCommunication extends JavaPlugin {

    private static NoCommunication instance;

    public static NoCommunication getInstance() {
        return instance;
    }

    public NoCommunication() {
        super();

        instance = this;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        PermissionManager.getInstance().load();
        MessageManager.getInstance().load();

        registerEvents();

        new Metrics(this, 19051);
    }

    @Override
    public void onDisable() {
        PermissionManager.getInstance().unload();
        MessageManager.getInstance().unload();
    }

    private void registerEvents() {
        if (this.getConfig().getBoolean("deny-sign")) {
            this.getServer().getPluginManager().registerEvents(new Sign(), this);
        }

        if (this.getConfig().getBoolean("deny-book")) {
            this.getServer().getPluginManager().registerEvents(new Book(), this);
        }

        if (this.getConfig().getBoolean("deny-item-rename")) {
            this.getServer().getPluginManager().registerEvents(new ItemRename(), this);
        }

        if (this.getConfig().getBoolean("deny-chat-message")) {
            this.getServer().getPluginManager().registerEvents(new ChatMessage(), this);
        }

        if (this.getConfig().getBoolean("deny-chat-command")) {
            this.getServer().getPluginManager().registerEvents(new ChatCommand(), this);
        }
    }

}
