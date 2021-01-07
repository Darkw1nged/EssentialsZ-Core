package me.darkwinged.Essentials.Utils.Lang;

import org.bukkit.entity.Player;

public enum Errors {

    NoPermission, Console, SpecifyPlayer, NoPlayerFound, Cooldown, CooldownItem, SenderInstaceOfPlayer,

    MessageEmpty,

    NoAccount, InvalidAmount, NotEnoughMoney, UsageEconomy, UsagePay, NoPlacePlayerHead, NoPouch, InvalidPouch,

    TPUsage, TPhereUsage, noSpawn, NoPreviousLocation,

    NoWarpNameProvided, WarpDoesNotExist, WarpAlreadyExist, NoWarpsFound,
    NoHomeNameProvided, NoHomes, HomeDoesNotExist, HomeAlreadyExist,

    GamemodeUsage, WorldGenUsage, InvalidWorld;

    public static String getErrors(Errors type) {
        String errors = "";
        switch (type) {
            case NoPermission:
                errors = Utils.chat("&cError! You do not have permission to use this command!");
                break;
            case Console:
                errors = Utils.chat("&cError! You can not do this command in console!");
                break;
            case SpecifyPlayer:
                errors = Utils.chat("&cError! Please specify a player.");
                break;
            case NoPlayerFound:
                errors = Utils.chat("&cError! Player could not be found.");
                break;
            case Cooldown:
                errors = Utils.chat("&cError! You need to wait before you can use this command again.");
                break;
            case CooldownItem:
                errors = Utils.chat("&cError! You need to wait before you can use this item again.");
                break;
            case SenderInstaceOfPlayer:
                errors = Utils.chat("&cError! Please choose another player");
                break;

            case MessageEmpty:
                errors = Utils.chat("&cError! Your message can not be empty.");
                break;

            case NoAccount:
                errors = Utils.chat("&cError! That player does not have an account.");
                break;
            case InvalidAmount:
                errors = Utils.chat("&cError! Invalid number!");
                break;
            case NotEnoughMoney:
                errors = Utils.chat("&cError! Sorry you do not have enough money for this.");
                break;
            case UsageEconomy:
                errors = Utils.chat("&cError! Usage: /economy <add/remove/set> <player> <amount>");
                break;
            case UsagePay:
                errors = Utils.chat("&cError! Usage: /pay <player> <amount>");
                break;
            case NoPlacePlayerHead:
                errors = Utils.chat("&cError! You can not place a players head!");
                break;
            case NoPouch:
                errors = Utils.chat("&cError! Please specify a money pouch tier.");
                break;
            case InvalidPouch:
                errors = Utils.chat("&cError! That money pouch does not exist.");
                break;

            case TPUsage:
                errors = Utils.chat("&cError! Usage: /tp <player> OR /tp <player> <player>");
                break;
            case TPhereUsage:
                errors = Utils.chat("&cError! Usage: /tphere <player>");
                break;
            case noSpawn:
                errors = Utils.chat("&cError! Spawn has not been set.");
                break;
            case NoPreviousLocation:
                errors = Utils.chat("&cError! Could not find a previous location.");
                break;
            case NoWarpNameProvided:
                errors = Utils.chat("&cError! Please specify a warp name.");
                break;
            case WarpDoesNotExist:
                errors = Utils.chat("&cError! That warp does not exist.");
                break;
            case WarpAlreadyExist:
                errors = Utils.chat("&cError! That warp already exists.");
                break;
            case NoWarpsFound:
                errors = Utils.chat("&cError! No Warps found.");
                break;
            case NoHomeNameProvided:
                errors = Utils.chat("&cError! Please specify a home name.");
                break;
            case NoHomes:
                errors = Utils.chat("&cError! Please set a home first.");
                break;
            case HomeDoesNotExist:
                errors = Utils.chat("&cError! That home does not exist.");
                break;
            case HomeAlreadyExist:
                errors = Utils.chat("&cError! A home already exists with that name.");
                break;

            case GamemodeUsage:
                errors = Utils.chat("&cError! Usage: /gamemode <gamemode> or /gamemode <player> <gamemode>");
                break;
            case WorldGenUsage:
                errors = Utils.chat("&cError! Usage: /world create <name> [normal, nether, end] [normal, flat, amplified, largebiomes] [Hardcore:true |" +
                        " false] [Generate Structures:true | false]");
                break;
            case InvalidWorld:
                errors = Utils.chat("&cError! That is not a valid world or it could not be found.");
                break;
        }
        return errors;
    }


}
