package com.hotmail.hboutilier1996.MineSkills;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MineSkills extends JavaPlugin{
    public static final Logger log = Logger.getLogger("Minecraft");
    public File mainConfigFile;
    public YamlConfiguration mainConfig;
    public File skillConfigFile;
    public YamlConfiguration skillConfig;
    public File playerConfigFile;
    public YamlConfiguration playerConfig;
    final double mathEBase = 2.718281828459045;
    final double exponentMult = 0.062146080984221;
    private BlockListeners bl;
    private EntityListeners el;
    private InventoryListeners il;
    private OtherListeners ol;
    private PlayerListeners pl;
    private VehicleListeners vl;
    @Override public void onEnable(){
        loadConfigs();
        getServer().getPluginManager().registerEvents(bl, this);
        getServer().getPluginManager().registerEvents(el, this);
        getServer().getPluginManager().registerEvents(il, this);
        getServer().getPluginManager().registerEvents(ol, this);
        getServer().getPluginManager().registerEvents(pl, this);
        getServer().getPluginManager().registerEvents(vl, this);
    }
    @Override public void onDisable(){
        saveConfigs(null);
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
    public void loadConfigs(){
        mainConfigFile = new File(new File(getDataFolder().getParentFile(), "MineJobs"), "config.yml");
        skillConfigFile = new File(new File(getDataFolder().getParentFile(), "MineJobs"), "skills.yml");
        playerConfigFile = new File(new File(getDataFolder().getParentFile(), "MineJobs"), "players.yml");
        if (!mainConfigFile.exists()) saveDefaultConfig();
        if (!skillConfigFile.exists()) saveResource("jobs.yml", false);
        if (!playerConfigFile.exists()) saveResource("players.yml", false);
        mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
        skillConfig = YamlConfiguration.loadConfiguration(skillConfigFile);
        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
    }
    public void saveConfigs(CommandSender sender){
        try{
            mainConfig.save(new File(new File(getDataFolder().getParentFile(), "MineJobs"), "config.yml"));
            skillConfig.save(new File(new File(getDataFolder().getParentFile(), "MineJobs"), "skills.yml"));
            playerConfig.save(new File(new File(getDataFolder().getParentFile(), "MineJobs"), "players.yml"));
        } catch (IOException e){
            String message = "SEVERE: MineSkills: Could not save config. Try command again; if fails persist, contact eNnillaMS on BukkitDev for assistance.";
            log.severe(message);
            if (sender != null){
                sender.sendMessage(ChatColor.DARK_RED + message);
            }
        }
    }
    
    public String getLevelStyle (String JobId){
        return skillConfig.getString("skills." + JobId + ".levelstyle").toUpperCase();
    }
    public int getEXP (String JobId, Player player){
        return playerConfig.getInt("players." + player.getName().toLowerCase() + "." + JobId);
    }
    public int getEXP (int level, String levelStyle){
        int exp;
        switch (levelStyle){
            case "LINEAR":
                exp = level * 10000;
                break;
            default:
            case "EXPONENTIAL":
                exp = (int) Math.floor(2000 * Math.pow(mathEBase, level * exponentMult));
                break;
        }
        return exp;
    }
    public int getLevel (String JobId, Player player){
        int plyrXP = getEXP(JobId, player);
        int plyrLVL;
        switch (getLevelStyle(JobId)){
            case "LINEAR":
                plyrLVL = (int) Math.floor(plyrXP / 10000);
                break;
            default:
            case "EXPONENTIAL":
                plyrLVL = (int) Math.floor((Math.log(plyrXP / 2000) / Math.log(mathEBase)) / exponentMult);
                break;
        }
        return plyrLVL;
    }
    public int getEXPtoNext (String JobId, Player player){
        int curXP = getEXP(JobId, player);
        int nxtLvl = getNextLevel(JobId, player);
        int nxtXP = getEXP(nxtLvl, getLevelStyle(JobId));
        return nxtXP - curXP;
    }
    public int getNextLevel (String JobId, Player player){
        return getLevel(JobId, player) + 1;
    }
}