package me.darkwinged.Essentials;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import me.darkwinged.Essentials.REWORK.Commands.Chat.*;
import me.darkwinged.Essentials.REWORK.Commands.Economy.*;
import me.darkwinged.Essentials.REWORK.Commands.Teleport.Staff.*;
import me.darkwinged.Essentials.REWORK.Commands.Teleport.*;
import me.darkwinged.Essentials.REWORK.Commands.World.*;
import me.darkwinged.Essentials.REWORK.Commands.World.Gamemodes.*;
import me.darkwinged.Essentials.REWORK.Commands.cmd_Reload;
import me.darkwinged.Essentials.REWORK.Events.Chat.*;
import me.darkwinged.Essentials.REWORK.Events.Chat.Color;
import me.darkwinged.Essentials.REWORK.Events.Economy.AccountSetup;
import me.darkwinged.Essentials.REWORK.Events.Economy.BankNotes;
import me.darkwinged.Essentials.REWORK.Events.Economy.MoneyPouchesEvent;
import me.darkwinged.Essentials.REWORK.Events.Economy.PlayerHeads;
import me.darkwinged.Essentials.REWORK.Events.Signs.BalanceSign;
import me.darkwinged.Essentials.REWORK.Events.Signs.GamemodeSign;
import me.darkwinged.Essentials.REWORK.Events.World.Tablist;
import me.darkwinged.Essentials.REWORK.Events.Teleport.Back;
import me.darkwinged.Essentials.REWORK.Events.Teleport.NoVoid;
import me.darkwinged.Essentials.REWORK.Events.Teleport.OnRespawn;
import me.darkwinged.Essentials.REWORK.Events.Teleport.SpawnOnJoin;
import me.darkwinged.Essentials.REWORK.Events.World.*;
import me.darkwinged.Essentials.REWORK.Utils.*;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.AutoMessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.BlockedCommandsFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.ChatFilterFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Economy.AccountsFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Chat.MessagesFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Economy.MoneyPouchFile;
import me.darkwinged.Essentials.REWORK.Utils.CustomFiles.Telepotation.SpawnFile;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang.math.RandomUtils.nextInt;
import static org.bukkit.Material.getMaterial;

public final class Main extends JavaPlugin implements Listener {

    public int Delay = getConfig().getInt("Teleportation_Delay");
    public boolean Cancel_TNT = false;
    public ProtocolManager protocolManager;
    private MoneyPouchFile moneyPouchFile;
    private MessagesFile messagesFile;
    private AccountsFile accountsFile;
    private ChatFilterFile chatFilterFile;
    private BlockedCommandsFile blockedCommandsFile;
    private AutoMessagesFile autoMessagesFile;
    private SpawnFile spawnFile;

    // REWORK
    public void onEnable() {
        // Console Start Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&aEssentialsZ plugin has been enabled!"));
        getServer().getConsoleSender().sendMessage(Utils.chat("&aFun Fact! Essentials was made by darkwinged!"));
        // Getting ProtocolLib, Teleport Utils, MetricsLite
        this.protocolManager = ProtocolLibrary.getProtocolManager();
        TeleportUtils teleportUtils = new TeleportUtils(this);
        MetricsLite metricsLite = new MetricsLite(this, 9811);
        // Adding BungeeCord to plugin
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // Loading Files
        loadConfig();
        loadCustomFiles();

        // Loading content to ArrayList(s)
        loadMoneyPouches();
        loadAutoMessages();

        // Adding EconomyManager / loading Accounts
        new EconomyManager(this);
        loadAccounts();

        // Registering Commands / Events / Loops
        registerCommands();
        registerEvents();
        LagCheck();
        AutoMessage();
        HidePlayersCheck();
        vanishCheck();
        vanishAndHidePlayersCheck();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders(this).register();
        }

        // Getting TPS
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
    }

    public void onDisable() {
        // Console Stop Message
        getServer().getConsoleSender().sendMessage(Utils.chat("&cEssentialsZ plugin has been disabled!"));
        getServer().getConsoleSender().sendMessage(Utils.chat("&cDarkwinged says 'goodbye'!"));
        // Saving the accounts for all of the players
        saveAccounts();
        // Saving the accounts file
        accountsFile.saveConfig();
    }

    @SuppressWarnings("ConstantConditions")
    public void registerCommands() {
        getCommand("essentials").setExecutor(new cmd_Reload(this, moneyPouchFile, messagesFile, accountsFile, chatFilterFile, blockedCommandsFile, spawnFile));

        // Economy
        getCommand("balance").setExecutor(new cmd_Balance(this));
        getCommand("pay").setExecutor(new cmd_Pay(messagesFile, this));
        getCommand("withdraw").setExecutor(new cmd_Withdraw(this));
        getCommand("economy").setExecutor(new cmd_Economy(this));
        getCommand("pouches").setExecutor(new cmd_MoneyPouches(messagesFile, moneyPouchFile, this));

        // Chat
        getCommand("staffchat").setExecutor(new cmd_Staffchat(this));
        getCommand("clearchat").setExecutor(new cmd_Clearchat(messagesFile, this));
        getCommand("broadcast").setExecutor(new cmd_Broadcast(messagesFile, this));
        getCommand("mutechat").setExecutor(new cmd_Mutechat(messagesFile, this));
        getCommand("motd").setExecutor(new cmd_MOTD(this));
        getCommand("sudo").setExecutor(new cmd_Sudo(this));

        // Teleportation
        getCommand("tp").setExecutor(new cmd_TP(messagesFile, this));
        getCommand("tphere").setExecutor(new cmd_TPhere(messagesFile, this));
        getCommand("spawn").setExecutor(new cmd_Spawn(messagesFile, spawnFile, this));
        getCommand("setspawn").setExecutor(new cmd_SetSpawn(messagesFile, spawnFile, this));
        getCommand("hub").setExecutor(new cmd_Hub(this));
        getCommand("rtp").setExecutor(new cmd_RandomTeleport(this));
        getCommand("top").setExecutor(new cmd_Top(this));
        getCommand("back").setExecutor(new cmd_Back(this, messagesFile));
        getCommand("setwarp").setExecutor(new cmd_SetWarp(messagesFile, this));
        getCommand("delwarp").setExecutor(new cmd_DelWarp(messagesFile, this));
        getCommand("warp").setExecutor(new cmd_Warp(messagesFile, this));
        getCommand("warps").setExecutor(new cmd_Warps(this));
        getCommand("sethome").setExecutor(new cmd_SetHome(messagesFile, this));
        getCommand("delhome").setExecutor(new cmd_DelHome(messagesFile, this));
        getCommand("home").setExecutor(new cmd_Home(messagesFile, this));
        getCommand("homes").setExecutor(new cmd_Homes(this));

        // World
        getCommand("hat").setExecutor(new cmd_Hat(this));
        getCommand("ping").setExecutor(new cmd_Ping(messagesFile, this));
        getCommand("gma").setExecutor(new cmd_AdventureMode(messagesFile, this));
        getCommand("gmc").setExecutor(new cmd_CreativeMode(messagesFile, this));
        getCommand("gms").setExecutor(new cmd_SurvivalMode(messagesFile, this));
        getCommand("gmsp").setExecutor(new cmd_SpectatorMode(messagesFile, this));
        getCommand("gamemode").setExecutor(new cmd_Gamemode(messagesFile, this));
        getCommand("invsee").setExecutor(new cmd_Invsee(this));
        getCommand("vanish").setExecutor(new cmd_Vanish(this, messagesFile));
        getCommand("reward").setExecutor(new cmd_Reward(this));
        getCommand("repair").setExecutor(new cmd_Repair(this));
        getCommand("world").setExecutor(new cmd_WorldGenerator(this));
        getCommand("clearlag").setExecutor(new cmd_ClearLag(this, messagesFile));
        getCommand("enderchest").setExecutor(new cmd_Enderchest(this));

    }

    public void registerEvents() {
        // Registering events in this class
        getServer().getPluginManager().registerEvents(this, this);

        // Chat Events
        getServer().getPluginManager().registerEvents(new ChatFilterEvent(chatFilterFile, this), this);
        getServer().getPluginManager().registerEvents(new BlockedCommandsEvent(blockedCommandsFile, this), this);
        getServer().getPluginManager().registerEvents(new MuteChat(messagesFile, this), this);
        getServer().getPluginManager().registerEvents(new JoinAndLeaveMessage(messagesFile, this), this);
        getServer().getPluginManager().registerEvents(new Color(this), this);
        getServer().getPluginManager().registerEvents(new AntiSpam(this), this);
        getServer().getPluginManager().registerEvents(new Tablist(this), this);
        getServer().getPluginManager().registerEvents(new ChatPing(this), this);

        // Economy Events
        getServer().getPluginManager().registerEvents(new PlayerHeads(this), this);
        getServer().getPluginManager().registerEvents(new AccountSetup(this), this);
        getServer().getPluginManager().registerEvents(new BankNotes(this), this);
        getServer().getPluginManager().registerEvents(new MoneyPouchesEvent(moneyPouchFile, this), this);

        // SIGN EVENTS
        getServer().getPluginManager().registerEvents(new BalanceSign(this), this);
        getServer().getPluginManager().registerEvents(new GamemodeSign(this), this);

        // TELEPORTATION EVENTS
        getServer().getPluginManager().registerEvents(new NoVoid(spawnFile, this), this);
        getServer().getPluginManager().registerEvents(new OnRespawn(spawnFile, this), this);
        getServer().getPluginManager().registerEvents(new SpawnOnJoin(spawnFile, this), this);
        getServer().getPluginManager().registerEvents(new Back(messagesFile, this), this);

        // WORLD EVENTS
        getServer().getPluginManager().registerEvents(new FireworkOnJoin(this), this);
        getServer().getPluginManager().registerEvents(new NoHopperCraft(this), this);
        getServer().getPluginManager().registerEvents(new NoHungerLoss(this), this);
        getServer().getPluginManager().registerEvents(new NoItemDropAndPickup(this), this);
        getServer().getPluginManager().registerEvents(new NoPlaceBreakBlocks(this), this);
        getServer().getPluginManager().registerEvents(new ChangeMOTD(this), this);
        getServer().getPluginManager().registerEvents(new DoubleJump(this), this);
        getServer().getPluginManager().registerEvents(new PlayerEatEvent(this), this);
        getServer().getPluginManager().registerEvents(new NoFallDamage(this), this);
        getServer().getPluginManager().registerEvents(new NoExperience(this), this);
        getServer().getPluginManager().registerEvents(new EnderPearlCooldown(this), this);
        getServer().getPluginManager().registerEvents(new VanishOnJoin(this), this);
        getServer().getPluginManager().registerEvents(new CommandCooldown(this), this);
        getServer().getPluginManager().registerEvents(new cmd_Invsee(this), this);
        getServer().getPluginManager().registerEvents(new LagEvent(this), this);
        getServer().getPluginManager().registerEvents(new HidePlayersItem(this), this);

    }

    // Economy Account Manager
    public void saveAccounts() {
        for (Map.Entry<String, Double> entry : EconomyManager.getAccountMap().entrySet()) {
            accountsFile.getConfig().set("Accounts." + entry.getKey(), entry.getValue());
        }
    }
    public void loadAccounts() {
        if (!accountsFile.getConfig().contains("Accounts.")) return;
        for (String key : accountsFile.getConfig().getConfigurationSection("Accounts.").getKeys(false)) {
            EconomyManager.getAccountMap().put(key, accountsFile.getConfig().getDouble("Accounts." + key));
        }
    }

    // Loading the config and custom files to the server
    public void loadConfig() {
        Utils.config = getConfig();
        Utils.config.options().copyDefaults(true);
        saveDefaultConfig();
        Utils.cfile = new File(getDataFolder(), "config.yml");
    }
    public void loadCustomFiles() {
        // Economy
        accountsFile = new AccountsFile(this, "Economy/Accounts");
        accountsFile.saveDefaultConfig();
        moneyPouchFile = new MoneyPouchFile(this, "Economy/Money Pouches");
        moneyPouchFile.saveDefaultConfig();

        // Teleportation
        spawnFile = new SpawnFile(this, "Teleportation/Spawn");
        spawnFile.saveDefaultConfig();

        // World

        // Chat
        messagesFile = new MessagesFile(this, "Chat/Messages");
        messagesFile.saveDefaultConfig();
        blockedCommandsFile = new BlockedCommandsFile(this, "Chat/Blocked Commands");
        blockedCommandsFile.saveDefaultConfig();
        chatFilterFile = new ChatFilterFile(this, "Chat/Chat Filter");
        chatFilterFile.saveDefaultConfig();
        autoMessagesFile = new AutoMessagesFile(this, "Chat/Auto Messages");
        autoMessagesFile.saveDefaultConfig();
    }

    // Check if the server is lagging
    public void LagCheck() {
        if (getConfig().getBoolean("Server_Lag_Check", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                public void run() {
                    if (Lag.getTPS() <= getConfig().getInt("TPS")) {
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            World world = online.getWorld();
                            List<Entity> entList = world.getEntities();
                            for (Entity current : entList) {
                                if (current instanceof Item) {
                                    current.remove();
                                }
                                if (current instanceof Mob) {
                                    if (current.getCustomName() != null) return;
                                    current.remove();
                                }
                                if (current instanceof Player) return;
                            }
                        }
                    }
                }
            }, 0, 10);
            if (getConfig().getBoolean("Cancel_TNT", true)) {
                Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    public void run() {
                        if (Lag.getTPS() <= getConfig().getInt("TPS")) {
                            Cancel_TNT = true;
                        }
                        Cancel_TNT = false;
                    }
                }, 0, 10);
            }
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (getConfig().getBoolean("Clear_Lag", true)) {
                    int total = 0;
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        World world = online.getWorld();
                        List<Entity> entList = world.getEntities();
                        for (Entity current : entList) {
                            if (current instanceof Item) {
                                current.remove();
                            }
                            if (current instanceof Mob) {
                                if (current.getCustomName() != null) return;
                                current.remove();
                            }
                            if (current instanceof Player) {
                                total -= 1;
                            }
                            total += 1;
                        }
                    }
                    Bukkit.broadcastMessage(Utils.chat(messagesFile.getConfig().getString("Prefix") + messagesFile.getConfig().getString("Clear Lag Message")
                            .replaceAll("%n", "\n")
                            .replaceAll("%entity_amount%", ""+total)
                            .replaceAll("%time%",""+getConfig().getInt("Clear_Lag_Delay")/60)));
                }
            }
        }, 20 * getConfig().getInt("Clear_Lag_Delay"), 20 * getConfig().getInt("Clear_Lag_Delay"));
    }

    // Getting money pouches
    public List<String> getConvertedLore(String path) {
        List<String> oldList = moneyPouchFile.getConfig().getStringList(path + ".lore");
        List<String> newList = new ArrayList<>();
        for (String a : oldList)
            newList.add(ChatColor.translateAlternateColorCodes('&', a));
        return newList;
    }
    public void loadMoneyPouches() {
        if (!moneyPouchFile.getConfig().contains("Tiers.")) return;
        for (String key : moneyPouchFile.getConfig().getConfigurationSection("Tiers.").getKeys(false)) {
            ItemStack item = new ItemStack(getMaterial(moneyPouchFile.getConfig().getString("Tiers." + key + ".material")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Utils.chat(moneyPouchFile.getConfig().getString("Tiers." + key + ".name")));
            meta.setLore(getConvertedLore("Tiers." + key));
            item.setItemMeta(meta);

            Utils.MoneyPouches_max.put(item.getItemMeta().getDisplayName(), moneyPouchFile.getConfig().getInt("Tiers." + key + ".max"));
            Utils.MoneyPouches_min.put(item.getItemMeta().getDisplayName(), moneyPouchFile.getConfig().getInt("Tiers." + key + ".min"));
            Utils.MoneyPouches.put(item.getItemMeta().getDisplayName(), item);
        }
    }

    // Managing auto messages
    public void loadAutoMessages() {
        if (!autoMessagesFile.getConfig().contains("Messages.")) return;
        Utils.AutoMessages.addAll(autoMessagesFile.getConfig().getStringList("Messages"));
    }
    public void AutoMessage() {
        if (getConfig().getBoolean("Auto_Messages", true)) {
            for (Iterator<String> iterator = autoMessagesFile.getConfig().getConfigurationSection("Messages").getKeys(false).iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                String message = autoMessagesFile.getConfig().getString("Messages." + key + ".content").replaceAll("%n", "\n");
                long interval = autoMessagesFile.getConfig().getInt("Messages." + key + ".interval") * 60;
                Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    public void run() {
                        Bukkit.broadcastMessage(Utils.chat(message));
                    }
                }, 20L * interval, 20L * interval);
            }
        }
    }

    // File creation =====================================================================================
    // Warps File
    public FileConfiguration warps;
    public File warpsfile;

    public void LoadWarpsFile() {
        warpsfile = new File(getDataFolder(), "World/Warps.yml");
        warps = YamlConfiguration.loadConfiguration(warpsfile);
        saveWarps();
    }

    public void saveWarps() {
        try {
            warps.save(warpsfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getWarpsfile() {
        return warpsfile;
    }

    public FileConfiguration getWarps() {
        return warps;
    }

    // Homes file
    public FileConfiguration homes;
    public File homesfile;

    @EventHandler
    public void homes(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        homesfile = new File(getDataFolder(), "Homes.yml");
        homes = YamlConfiguration.loadConfiguration(homesfile);
        if (!homes.contains("Homes.Owner's Name " + player.getName())) {
            homes.set("Homes.Owner's Name " + player.getName(), "");
            saveHomes();
        }
    }

    public void saveHomes() {
        try {
            homes.save(homesfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getHomesFile() {
        return homesfile;
    }

    public FileConfiguration getHomes() {
        return homes;
    }

    public void HidePlayersCheck() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
              public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                	if (Utils.hide_player_list.contains(player.getUniqueId())) {
                		if (Utils.invisible_list.contains(player.getUniqueId())) return;
                        if (!getConfig().getBoolean("Hide Players.enabled", true)) return;
                        if (!getConfig().getBoolean("Hide Players.reminder", true)) return;
                		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(Utils.chat("&fAll players are currently &cHidden")));
                	}
                }
              }
            }, 0L, 20L);
      }

    public void vanishCheck() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                	if (Utils.invisible_list.contains(player.getUniqueId())) {
                		if (Utils.hide_player_list.contains(player.getUniqueId())) return;
                		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(Utils.chat("&f&lYou are in &c&lVanish")));
                	}
                }
            }
        }, 0L, 20L);
    }

    public void vanishAndHidePlayersCheck() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
              public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                	if (Utils.invisible_list.contains(player.getUniqueId()) && Utils.hide_player_list.contains(player.getUniqueId())) {
                		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(Utils.chat("&f&lYou are in &c&lVanish &f&land all players are &c&lHidden")));
                	}
                }
              }
            }, 0L, 20L);
      }
}
