package me.darkwinged.essentialsz.libaries.lang;

@SuppressWarnings("ALL")
public class Permissions {

    // Essential Permissions
    public static String MainPermission = "EssentialsZ.";
    public static String GlobalOverwrite = MainPermission + "*";
    public static String bypass = MainPermission + "Bypass";
    public static String Reload = MainPermission + "Reload";
    public static String TeleportBypass = MainPermission + "TeleportDelay.Bypass";
    public static String SafeLogin = MainPermission + "SafeLogin";

    // Economy Permissions
    public static String Economy = MainPermission + "Economy";
    public static String Balance = MainPermission + "Balance";
    public static String BalanceOther = MainPermission + "Balance.Other";
    public static String BalanceSign = MainPermission + "Balance.Sign.Create";
    public static String Pay = MainPermission + "Pay";
    public static String Withdraw = MainPermission + "Withdraw";
    public static String MoneyPouch = MainPermission + "MoneyPouch";
    public static String Autosell = MainPermission + "Autosell";
    public static String SellHand = MainPermission + "Sellhand";
    public static String Sell = MainPermission + "Sell";

    // Chat Permissions

    // Chat Color Permissions =====================================================================
    public static String ChatColor = MainPermission + "ChatColor";
    public static String ChatColor_black = MainPermission + "ChatColor.Black";
    public static String ChatColor_dark_blue = MainPermission + "ChatColor.DarkBlue";
    public static String ChatColor_dark_green = MainPermission + "ChatColor.DarkGreen";
    public static String ChatColor_dark_aqua = MainPermission + "ChatColor.DarkAqua";
    public static String ChatColor_dark_red = MainPermission + "ChatColor.DarkRed";
    public static String ChatColor_dark_purple = MainPermission + "ChatColor.DarkPurple";
    public static String ChatColor_gold = MainPermission + "ChatColor.Gold";
    public static String ChatColor_gray = MainPermission + "ChatColor.Gray";
    public static String ChatColor_dark_gray = MainPermission + "ChatColor.DarkGray";
    public static String ChatColor_blue = MainPermission + "ChatColor.Blue";
    public static String ChatColor_green = MainPermission + "ChatColor.Green";
    public static String ChatColor_aqua = MainPermission + "ChatColor.Aqua";
    public static String ChatColor_red = MainPermission + "ChatColor.Red";
    public static String ChatColor_light_purple = MainPermission + "ChatColor.LightPurple";
    public static String ChatColor_yellow = MainPermission + "ChatColor.Yellow";
    public static String ChatColor_white = MainPermission + "ChatColor.White";

    public static String ChatColor_magic = MainPermission + "ChatColor.Magic";
    public static String ChatColor_bold = MainPermission + "ChatColor.Bold";
    public static String ChatColor_strikethrough = MainPermission + "ChatColor.Strikethrough";
    public static String ChatColor_underline = MainPermission + "ChatColor.Underline";
    public static String ChatColor_italic = MainPermission + "ChatColor.Italic";
    public static String ChatColor_reset = MainPermission + "ChatColor.Reset";

    // Sign Color Permissions =====================================================================
    public static String SignColor = MainPermission + "SignColor";
    public static String SignColor_black = MainPermission + "SignColor.Black";
    public static String SignColor_dark_blue = MainPermission + "SignColor.DarkBlue";
    public static String SignColor_dark_green = MainPermission + "SignColor.DarkGreen";
    public static String SignColor_dark_aqua = MainPermission + "SignColor.DarkAqua";
    public static String SignColor_dark_red = MainPermission + "SignColor.DarkRed";
    public static String SignColor_dark_purple = MainPermission + "SignColor.DarkPurple";
    public static String SignColor_gold = MainPermission + "SignColor.Gold";
    public static String SignColor_gray = MainPermission + "SignColor.Gray";
    public static String SignColor_dark_gray = MainPermission + "SignColor.DarkGray";
    public static String SignColor_blue = MainPermission + "SignColor.Blue";
    public static String SignColor_green = MainPermission + "SignColor.Green";
    public static String SignColor_aqua = MainPermission + "SignColor.Aqua";
    public static String SignColor_red = MainPermission + "SignColor.Red";
    public static String SignColor_light_purple = MainPermission + "SignColor.LightPurple";
    public static String SignColor_yellow = MainPermission + "SignColor.Yellow";
    public static String SignColor_white = MainPermission + "SignColor.White";

    public static String SignColor_magic = MainPermission + "SignColor.Magic";
    public static String SignColor_bold = MainPermission + "SignColor.Bold";
    public static String SignColor_strikethrough = MainPermission + "SignColor.Strikethrough";
    public static String SignColor_underline = MainPermission + "SignColor.Underline";
    public static String SignColor_italic = MainPermission + "SignColor.Italic";
    public static String SignColor_reset = MainPermission + "SignColor.Reset";
    // ============================================================================================

    public static String StaffChat = MainPermission + "StaffChat";
    public static String ClearChat = MainPermission + "ClearChat";
    public static String Broadcast = MainPermission + "Broadcast";
    public static String MuteChat = MainPermission + "MuteChat";
    public static String Message = MainPermission + "Message";
    public static String Reply = MainPermission + "Reply";

    public static String Ping = MainPermission + "Ping";
    public static String PingOther = MainPermission + "Ping.Other";
    public static String Sudo = MainPermission + "Sudo";

    public static String VIPJoin = MainPermission + "VIP";

    // Teleportation Permissions
    public static String TP = MainPermission + "teleport";
    public static String TPhere = MainPermission + "teleport.here";
    public static String TPhereAll = MainPermission + "teleport.here.all";
    public static String TPhereEntities = MainPermission + "teleport.here.entities";
    public static String TPA = MainPermission + "teleport.ask";
    public static String RandomTeleport = MainPermission + "RTP";
    public static String TopTeleport = MainPermission + "Top";
    public static String UpSign = MainPermission + "Top.Sign.Create";
    public static String BackTeleport = MainPermission + "Back";

    public static String WarpsOverwrite = MainPermission + "Warps.*";
    public static String DelWarps = MainPermission + "Warps.Remove";
    public static String SetWarps = MainPermission + "Warps.Set";
    public static String Warps = MainPermission + "Warps.List";
    public static String Warp = MainPermission + "Warps.";
    public static String WarpsSign = MainPermission + "Warps.Sign.Create";

    public static String HomesOverwrite = MainPermission + "Homes.*";
    public static String DelHomes = MainPermission + "Homes.Remove";
    public static String DelHomesOther = MainPermission + "Homes.Remove.Other";
    public static String SetHomes = MainPermission + "Homes.Set";
    public static String SetHomesVIP = MainPermission + "Homes.Set.VIP";
    public static String SetHomesStaff = MainPermission + "Homes.Set.Staff";
    public static String Homes = MainPermission + "Homes.List";
    public static String HomesOther = MainPermission + "Homes.List.Other";
    public static String Home = MainPermission + "Homes";
    public static String HomesSign = MainPermission + "Homes.Sign.Create";

    // World Permissions
    public static String ChangeMOTD = MainPermission + "MOTD";
    public static String Hat = MainPermission + "Hat";
    public static String DoubleJump = MainPermission + "DoubleJump";
    public static String Invsee = MainPermission + "Invsee";
    public static String Vanish = MainPermission + "Vanish";
    public static String SeeVanish = MainPermission + "Vanish.See";
    public static String SilentJoin = MainPermission + "Join.Silent";

    public static String Repair = MainPermission + "Repair";
    public static String RepairAll = MainPermission + "Repair.All";
    public static String RepairChest = MainPermission + "Repair.Chest";

    public static String GamemodeGlobal = MainPermission + "Gamemode.*";
    public static String AdventureMode = MainPermission + "Gamemode.Adventure";
    public static String AdventureModeOther = MainPermission + "Gamemode.Adventure.Other";
    public static String CreativeMode = MainPermission + "Gamemode.Creative";
    public static String CreativeModeOther = MainPermission + "Gamemode.Creative.Other";
    public static String SurvivalMode = MainPermission + "Gamemode.Survival";
    public static String SurvivalModeOther = MainPermission + "Gamemode.Survival.Other";
    public static String SpectatorMode = MainPermission + "Gamemode.Spectator";
    public static String SpectatorModeOther = MainPermission + "Gamemode.Spectator.Other";
    public static String GamemodeSign = MainPermission + "Gamemode.Sign.Create";

    public static String CreateWorld = MainPermission + "World.Create";
    public static String RemoveWorld = MainPermission + "World.Remove";
    public static String Enderchest = MainPermission + "Enderchest";
    public static String EnderchestOther = MainPermission + "Enderchest.Other";
    public static String Disposal = MainPermission + "Disposal";
    public static String Craft = MainPermission + "Craft";
    public static String Anvil = MainPermission + "Anvil";
    public static String Kill = MainPermission + "Kill";
    public static String God = MainPermission + "God";
    public static String Heal = MainPermission + "Heal";
    public static String HealOther = MainPermission + "Heal.Other";
    public static String Feed = MainPermission + "Feed";
    public static String FeedOther = MainPermission + "Feed.Other";
    public static String Fly = MainPermission + "Fly";
    public static String FlyOther = MainPermission + "Fly.Other";
    public static String Smite = MainPermission + "Smite";
    public static String RidePlayer = MainPermission + "Ride.Player";
    public static String CPS = MainPermission + "CPS.Check";
    public static String WorldWeather = MainPermission + "Weather.World";
    public static String PlayerWeather = MainPermission + "Weather.Player";

    public static String TNTFillSurvival = MainPermission + "TNT.Fill";
    public static String TNTFillCreative = MainPermission + "TNT.Fill.Creative";
}
