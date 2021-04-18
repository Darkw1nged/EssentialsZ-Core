package me.darkwinged.EssentialsZ.Libaries.Lang;

import me.darkwinged.EssentialsZ.Main;

public enum Errors {

    NoPermission, Console, SpecifyPlayer, NoPlayerFound, Cooldown, CooldownItem, SenderInstaceOfPlayer, DisabledCommand,

    MessageEmpty,

    UnableToFill, NoDispensersNearby, NoTNT,

    NoAccount, InvalidAmount, NotEnoughMoney, UsageEconomy, UsagePay, NoPlacePlayerHead, NoPouch, InvalidPouch, SellUsage,

    TPUsage, TPhereUsage, NoPreviousLocation,

    NoWarpNameProvided, WarpDoesNotExist, WarpAlreadyExist, NoWarpsFound,
    NoHomeNameProvided, NoHomes, HomeDoesNotExist, HomeAlreadyExist,

    GamemodeUsage, WorldGenUsage, InvalidWorld, InvalidGameMode, FullFood, Length;

    private static final Main plugin = Main.getInstance;

    public static String getErrors(Errors type) {
        String errors = "";
        switch (type) {
            case NoPermission:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You do not have permission to use this command!", null, null, null, false);
                break;
            case Console:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You can not do this command in console!", null, null, null, false);
                break;
            case SpecifyPlayer:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please specify a player.", null, null, null, false);
                break;
            case NoPlayerFound:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Player could not be found.", null, null, null, false);
                break;
            case Cooldown:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You need to wait before you can use this command again.", null, null, null, false);
                break;
            case CooldownItem:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You need to wait before you can use this item again.", null, null, null, false);
                break;
            case SenderInstaceOfPlayer:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please choose another player", null, null, null, false);
                break;
            case DisabledCommand:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Server has disabled this command!", null, null, null, false);
                break;

            case MessageEmpty:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Your message can not be empty.", null, null, null, false);
                break;

            case UnableToFill:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Unable to fill dispensers.", null, null, null, false);
                break;
            case NoDispensersNearby:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Unable to find nearby dispensers", null, null, null, false);
                break;
            case NoTNT:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You have no TNT in your inventory.", null, null, null, false);
                break;

            case NoAccount:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That player does not have an account.", null, null, null, false);
                break;
            case InvalidAmount:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Invalid number!", null, null, null, false);
                break;
            case NotEnoughMoney:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Sorry you do not have enough money for this.", null, null, null, false);
                break;
            case UsageEconomy:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /economy <add/remove/set/reset> <player> <amount>", null, null, null, false);
                break;
            case UsagePay:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /pay <player> <amount>", null, null, null, false);
                break;
            case NoPlacePlayerHead:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You can not place a players head!", null, null, null, false);
                break;
            case NoPouch:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please specify a money pouch tier.", null, null, null, false);
                break;
            case InvalidPouch:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That money pouch does not exist.", null, null, null, false);
                break;
            case SellUsage:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /sell [hand/all]", null, null, null, false);
                break;

            case TPUsage:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /tp <player> OR /tp <player> <player>", null, null, null, false);
                break;
            case TPhereUsage:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /tphere <player, @e, @a>", null, null, null, false);
                break;
            case NoPreviousLocation:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Could not find a previous location.", null, null, null, false);
                break;
            case NoWarpNameProvided:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please specify a warp name.", null, null, null, false);
                break;
            case WarpDoesNotExist:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That warp does not exist.", null, null, null, false);
                break;
            case WarpAlreadyExist:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That warp already exists.", null, null, null, false);
                break;
            case NoWarpsFound:
                errors = plugin.essentialsZAPI.utils.chat("&cError! No Warps found.", null, null, null, false);
                break;
            case NoHomeNameProvided:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please specify a home name.", null, null, null, false);
                break;
            case NoHomes:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please set a home first.", null, null, null, false);
                break;
            case HomeDoesNotExist:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That home does not exist.", null, null, null, false);
                break;
            case HomeAlreadyExist:
                errors = plugin.essentialsZAPI.utils.chat("&cError! A home already exists with that name.", null, null, null, false);
                break;

            case GamemodeUsage:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /gamemode <gamemode> or /gamemode <player> <gamemode>", null, null, null, false);
                break;
            case WorldGenUsage:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Usage: /world create <name> [normal, nether, end] [normal, flat, amplified, largebiomes] " +
                        "[Generate Structures:true | false]", null, null, null, false);
                break;
            case InvalidWorld:
                errors = plugin.essentialsZAPI.utils.chat("&cError! That is not a valid world or it could not be found.", null, null, null, false);
                break;
            case InvalidGameMode:
                errors = plugin.essentialsZAPI.utils.chat("&cError! You need to be in survival or adventure mode!", null, null, null, false);
                break;
            case FullFood:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Already full on food.", null, null, null, false);
                break;
            case Length:
                errors = plugin.essentialsZAPI.utils.chat("&cError! Please specify how long for.", null, null, null, false);
                break;
        }
        return errors;
    }


}
