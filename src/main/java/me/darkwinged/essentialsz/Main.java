package me.darkwinged.essentialsz;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.darkwinged.EssentialsZ.EssentialsZAPI;
import me.darkwinged.EssentialsZ.EssentialsZSpawn;
import me.darkwinged.essentialsz.commands.chat.*;
import me.darkwinged.essentialsz.commands.economy.*;
import me.darkwinged.essentialsz.commands.teleport.staff.*;
import me.darkwinged.essentialsz.commands.teleport.*;
import me.darkwinged.essentialsz.commands.world.gamemodes.*;
import me.darkwinged.essentialsz.commands.world.*;
import me.darkwinged.essentialsz.commands.ReloadCommand;
import me.darkwinged.essentialsz.events.chat.ChatControl;
import me.darkwinged.essentialsz.events.chat.Color;
import me.darkwinged.essentialsz.events.chat.Displayname;
import me.darkwinged.essentialsz.events.chat.joinmessage.DefaultJoinMessage;
import me.darkwinged.essentialsz.events.chat.joinmessage.OtherJoinMessage;
import me.darkwinged.essentialsz.events.chat.joinmessage.VIPJoinMessage;
import me.darkwinged.essentialsz.events.economy.AccountSetup;
import me.darkwinged.essentialsz.events.economy.BankNotes;
import me.darkwinged.essentialsz.events.economy.MoneyPouchesEvent;
import me.darkwinged.essentialsz.events.economy.PlayerHeads;
import me.darkwinged.essentialsz.events.PlayerData;
import me.darkwinged.essentialsz.events.signs.SignBalance;
import me.darkwinged.essentialsz.events.signs.SignGamemode;
import me.darkwinged.essentialsz.events.signs.SignUp;
import me.darkwinged.essentialsz.events.teleport.Back;
import me.darkwinged.essentialsz.events.teleport.NoVoid;
import me.darkwinged.essentialsz.events.teleport.OnRespawn;
import me.darkwinged.essentialsz.events.teleport.SpawnOnJoin;
import me.darkwinged.essentialsz.events.world.*;
import me.darkwinged.essentialsz.libaries.economy.EconomyManager;
import me.darkwinged.essentialsz.libaries.economy.EssentialsZEconomy;
import me.darkwinged.essentialsz.libaries.lang.CustomConfig;
import me.darkwinged.essentialsz.libaries.lang.MetricsLite;
import me.darkwinged.essentialsz.libaries.lang.Utils;
import me.darkwinged.essentialsz.libaries.PlaceHolders;
import me.darkwinged.essentialsz.libaries.TeleportUtils;
import me.darkwinged.essentialsz.libaries.VaultHook;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static Main getInstance;

    public EconomyManager     economyManager;
    public ProtocolManager    protocolManager;
    public EssentialsZEconomy economy_essentialsZ;
    public EssentialsZAPI     essentialsZAPI = (EssentialsZAPI) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZAPI");
    public EssentialsZSpawn   essentialsZSpawn = (EssentialsZSpawn) Bukkit.getServer().getPluginManager().getPlugin("EssentialsZSpawn");

    public int Delay = getConfig().getInt("Teleportation.Settings.Teleportation Delay");
    public boolean Module_Economy = false;

    private VaultHook vaultHook;
    private static Chat chat = null;

    // Feature Files
    public CustomConfig MoneyPouchesFile = new CustomConfig(this, "Features/Money Pouches", true);
    public CustomConfig ExperiencePouchesFile = new CustomConfig(this, "Features/Experience Pouches", true);
    public CustomConfig BlockedCommandsFile = new CustomConfig(this, "Features/Blocked Commands", true);
    public CustomConfig ChatFilterFile = new CustomConfig(this, "Features/Chat Filter", true);
    public CustomConfig AutoMessagesFile = new CustomConfig(this, "Features/Auto Messages", true);
    public CustomConfig CouponsFile = new CustomConfig(this, "Features/Coupons", true);

    public CustomConfig ServerDataFile = new CustomConfig(this, "Data/Server Data", true);
    public CustomConfig MessagesFile = new CustomConfig(this, "Messages", true);
    public CustomConfig WorthFile = new CustomConfig(this, "Worth", true);

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("EssentialsZAPI") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDisabling plugin, No EssentialsZ API Found."));
            getServer().getPluginManager().disablePlugin(this);
        }
        getInstance = this;

        // Loading Files
        loadConfig();
        loadCustomFiles();

        // Getting ProtocolLib, Teleport Utils, MetricsLite, PlaceholderAPI, Vault, EssentialsZ APi, Bungeecord, Adding EconomyManager
        new TeleportUtils();
        new MetricsLite(this, 9811);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // Vault hook ================================================
        economyManager = new EconomyManager();
        economy_essentialsZ = new EssentialsZEconomy();
        for (Player online : Bukkit.getOnlinePlayers()) {
            economyManager.loadBalance(online);
        }

        setupChat();
        vaultHook = new VaultHook();
        vaultHook.hook();
        //=============================================================

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolders().register();
        }
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            this.protocolManager = ProtocolLibrary.getProtocolManager();
        }

        loadAutoMessages();

        // Registering Commands / Events / Loops
        registerCommands();
        registerEvents();
        AutoMessage();
        vanishCheck();

        // Console Start Message
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&aEssentialsZ Core plugin has been enabled!",
                null, null, null, false));
    }

    public void onDisable() {
        // Saving accounts
        for (Player online : Bukkit.getOnlinePlayers()) {
            economyManager.saveBalance(online);
        }

        // Unhooking vault
        vaultHook.unhook();

        // Console Stop Message
        getServer().getConsoleSender().sendMessage(essentialsZAPI.utils.chat("&cEssentialsZ Core plugin has been disabled!",
                null, null, null, false));
    }

    public void registerCommands() {
        getCommand("essentials").setExecutor(new ReloadCommand());

        // Economy
        getCommand("economy").setExecutor(new EconomyCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("pouches").setExecutor(new MoneyPouchesCommand());
        //getCommand("autosell").setExecutor(new cmd_Autosell(this));
        //getCommand("sellhand").setExecutor(new cmd_Sellhand(this));
        //getCommand("sell").setExecutor(new cmd_Sell(this));

        // Chat
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("clearchat").setExecutor(new ClearchatCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("motd").setExecutor(new MOTDCommand());
        getCommand("sudo").setExecutor(new SudoCommand());

        // Teleportation
        getCommand("tp").setExecutor(new TeleportCommand());
        getCommand("tphere").setExecutor(new TeleportHereCommand());
        getCommand("hub").setExecutor(new HubCommand());
        getCommand("rtp").setExecutor(new RandomTeleportCommand());
        getCommand("top").setExecutor(new TopCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("homes").setExecutor(new HomesCommand());

        // World
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("gma").setExecutor(new AdventureModeCommand());
        getCommand("gmc").setExecutor(new CreativeModeCommand());
        getCommand("gms").setExecutor(new SurvivalModeCommand());
        getCommand("gmsp").setExecutor(new SpectatorModeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("repair").setExecutor(new RepairCommand());
        getCommand("world").setExecutor(new CreateWorldCommand());
        getCommand("delworld").setExecutor(new DelWorldCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("disposal").setExecutor(new DisposalCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("anvil").setExecutor(new AnvilCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("smite").setExecutor(new SmiteCommand());
        getCommand("filltnt").setExecutor(new TNTFillCommand());
        getCommand("cps").setExecutor(new CPSCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("pweather").setExecutor(new WeatherPlayerCommand());

    }

    public void registerEvents() {
        // Chat Events
        getServer().getPluginManager().registerEvents(new ChatControl(), this);
        getServer().getPluginManager().registerEvents(new DefaultJoinMessage(), this);
        getServer().getPluginManager().registerEvents(new VIPJoinMessage(), this);
        getServer().getPluginManager().registerEvents(new OtherJoinMessage(), this);
        getServer().getPluginManager().registerEvents(new Color(), this);
        getServer().getPluginManager().registerEvents(new Displayname(), this);

        // Economy Events
        getServer().getPluginManager().registerEvents(new PlayerHeads(), this);
        getServer().getPluginManager().registerEvents(new AccountSetup(), this);
        getServer().getPluginManager().registerEvents(new BankNotes(), this);
        getServer().getPluginManager().registerEvents(new MoneyPouchesEvent(), this);

        // SIGN EVENTS
        getServer().getPluginManager().registerEvents(new SignBalance(), this);
        getServer().getPluginManager().registerEvents(new SignGamemode(), this);
        getServer().getPluginManager().registerEvents(new SignUp(), this);

        // TELEPORTATION EVENTS
        getServer().getPluginManager().registerEvents(new NoVoid(), this);
        getServer().getPluginManager().registerEvents(new OnRespawn(), this);
        getServer().getPluginManager().registerEvents(new SpawnOnJoin(), this);
        getServer().getPluginManager().registerEvents(new Back(), this);

        // WORLD EVENTS
        getServer().getPluginManager().registerEvents(new NoItemDropAndPickup(), this);
        getServer().getPluginManager().registerEvents(new NoPlaceBreakBlocks(), this);
        getServer().getPluginManager().registerEvents(new ChangeMOTD(), this);
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(new GodCommand(), this);
        getServer().getPluginManager().registerEvents(new Tablist(), this);
        getServer().getPluginManager().registerEvents(new WorldControl(), this);
        getServer().getPluginManager().registerEvents(new Rider(), this);
        getServer().getPluginManager().registerEvents(new PlayerCoords(), this);
        getServer().getPluginManager().registerEvents(new Playtime(), this);
        getServer().getPluginManager().registerEvents(new NetherWater(), this);
        getServer().getPluginManager().registerEvents(new InventoryFull(), this);

        getServer().getPluginManager().registerEvents(new PlayerData(), this);
    }

    public void loadConfig() {
        // Loading the config and custom files to the server
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveDefaultConfig();
        new File(getDataFolder(), "config.yml");
    }
    public void loadCustomFiles() {
        ServerDataFile.saveDefaultConfig();
        MessagesFile.saveDefaultConfig();

        CouponsFile.saveDefaultConfig();
        MoneyPouchesFile.saveDefaultConfig();
        ExperiencePouchesFile.saveDefaultConfig();
        WorthFile.saveDefaultConfig();
        BlockedCommandsFile.saveDefaultConfig();
        ChatFilterFile.saveDefaultConfig();
        AutoMessagesFile.saveDefaultConfig();
    }

    // Managing auto messages
    public void loadAutoMessages() {
        if (!AutoMessagesFile.getConfig().contains("Messages.")) return;
        Utils.AutoMessages.addAll(AutoMessagesFile.getConfig().getStringList("Messages"));
    }
    public void AutoMessage() {
        if (getConfig().getBoolean("Chat.enabled", true)) {
            if (getConfig().getBoolean("Chat.Settings.Auto Messages", true)) {
                for (String key : AutoMessagesFile.getConfig().getConfigurationSection("Messages").getKeys(false)) {
                    String message = AutoMessagesFile.getConfig().getString("Messages." + key + ".content").replaceAll("%n", "\n");
                    long interval = (long) AutoMessagesFile.getConfig().getInt("Messages." + key + ".interval") * 60;
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                        if (AutoMessagesFile.getConfig().getBoolean("Messages." + key + ".center", true)) {
                            Bukkit.broadcastMessage(essentialsZAPI.utils.chat(essentialsZAPI.utils.CenteredMessage(message),
                                    null, null, null, false));
                            return;
                        }
                        Bukkit.broadcastMessage(essentialsZAPI.utils.chat(message, null, null, null, false));
                    }, 20L * interval, 20L * interval);
                }
            }
        }
    }

    // Checking if player is in vanish and alerting.
    public void vanishCheck() {
        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8") || Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.12")) return;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                CustomConfig Data = Utils.getDataFile(player);
                if (Data.getConfig().getBoolean("isVanished", true)) {
                    essentialsZAPI.utils.sendActionBar(player, "&fYou are in &cVanish");
                }
            }
        }, 0L, 20L);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp == null) return chat == null;
        chat = rsp.getProvider();
        return chat != null;
    }

    public Chat getChat() {
        return chat;
    }
}
