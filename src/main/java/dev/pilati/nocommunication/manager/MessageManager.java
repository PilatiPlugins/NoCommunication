package dev.pilati.nocommunication.manager;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.pilati.nocommunication.NoCommunication;
import net.md_5.bungee.api.ChatColor;

public class MessageManager {
    private static MessageManager instance;

    private String[] supportedLanguages = { "en", "br" };
    private FileConfiguration configMessages;

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }

        return instance;
    }

    public MessageManager() {
        super();
    }

    public void load() {

        try {
            MessageManager.getInstance().loadLanguageFile();
        } catch (Exception ex) {
            NoCommunication plugin = NoCommunication.getInstance();

            plugin.getLogger().severe("Could not load language file");
            ex.printStackTrace();
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    public void unload() {
        instance = null;
    }

    public void loadLanguageFile() throws Exception {
        NoCommunication plugin = NoCommunication.getInstance();

        for (String supportedLanguage : supportedLanguages) {

            File langFile = new File(plugin.getDataFolder() + File.separator + "Messages", supportedLanguage + ".yml");

            if (!langFile.exists()) {
                langFile.getParentFile().mkdirs();
                plugin.saveResource("Messages" + File.separator + supportedLanguage + ".yml", false);
            }
        }

        loadLanguageFile(plugin.getConfig().getString("language"));
    }

    public void loadLanguageFile(String language) throws Exception {
        NoCommunication plugin = NoCommunication.getInstance();

        File langFile = new File(plugin.getDataFolder() + File.separator + "Messages", language + ".yml");

        if (!langFile.exists()) {
            throw new Exception("Could not load language file");
        }

        configMessages = YamlConfiguration.loadConfiguration(langFile);

        if (configMessages == null) {
            throw new Exception("Could not load language file");
        }
    }

    public String getPrefix() {
        return getConstant("pluginPrefix");
    }

    public String getMessage(String key) {
        return getPrefix() + getConstant(key);
    }

    public String getConstant(String key) {
        String constant = configMessages.getString(key);

        if (constant == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', constant);
    }

    public void sendMessage(CommandSender sender, String key) {
        sender.sendMessage(getMessage(key));
    }
}
