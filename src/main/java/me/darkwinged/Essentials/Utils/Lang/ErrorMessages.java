package me.darkwinged.Essentials.Utils.Lang;

public class ErrorMessages {

    public static String NoPermission = Utils.chat("&cError! You do not have permission to use this command!");
    public static String Console = Utils.chat("&cError! You can not do this command in console!");
    public static String NoPlayer = Utils.chat("&cError! Please specify a player.");
    public static String NoPlayerFound = Utils.chat("&cError! Player could not be found.");
    public static String Cooldown = Utils.chat("&cError! You need to wait before you can use this command again.");
    public static String CooldownItem = Utils.chat("&cError! You need to wait before you can use this item again.");

    // Economy Messages
    public static String NoAccount = Utils.chat("&cError! That player does not have an account.");
    public static String InvalidAmount = Utils.chat("&cError! Invalid number!");
    public static String NotEnoughMoney = Utils.chat("&cError! Sorry you do not have enough money for this.");
    public static String UsageEconomy = Utils.chat("&cError! Usage: /economy <add/remove/set> <player> <amount>");
    public static String UsagePay = Utils.chat("&cError! Usage: /pay <player> <amount>");
    public static String NoPlacePlayerHead = Utils.chat("&cError! You can not place a players head!");
    public static String SenderInstaceOfPlayer = Utils.chat("&cError! Please choose another player");
    public static String NoPouch = Utils.chat("&cError! Please specify a money pouch tier.");
    public static String InvalidPouch = Utils.chat("&cError! That money pouch does not exist.");

    // Chat Messages
    public static String MessageEmpty = Utils.chat("&cError! Your message can not be empty.");

    // Teleportation Messages
    public static String TPUsage = Utils.chat("&cError! Usage: /tp <player> OR /tp <player> <player>");
    public static String TPhereUsage = Utils.chat("&cError! Usage: /tphere <player>");
    public static String noSpawn = Utils.chat("&cError! Spawn has not been set.");
    public static String NoPreviousLocation = Utils.chat("&cError! Could not find a previous location.");

    public static String NoWarpNameProvided = Utils.chat("&cError! Please specify a warp name.");
    public static String WarpDoesNotExist = Utils.chat("&cError! That warp does not exist.");
    public static String WarpAlreadyExist = Utils.chat("&cError! That warp already exists.");
    public static String NoWarpsFound = Utils.chat("&cError! No Warps found.");

    public static String NoHomeNameProvided = Utils.chat("&cError! Please specify a home name.");
    public static String NoHomes = Utils.chat("&cError! Please set a home first.");
    public static String HomeDoesNotExist = Utils.chat("&cError! That home does not exist.");
    public static String HomeAlreadyExist = Utils.chat("&cError! A home already exists with that name.");

    // World Messages
    public static String GamemodeUsage = Utils.chat("&cError! Usage: /gamemode <gamemode> or /gamemode <player> <gamemode>");
    public static String WorldGenUsage = Utils.chat("&cError! Usage: /world create <name> [normal, nether, end] [normal, flat, amplified, largebiomes] [Hardcore:true |" +
            " false] [Generate Structures:true | false]");
    public static String InvalidWorld = Utils.chat("&cError! That is not a valid world or it could not be found.");

}
