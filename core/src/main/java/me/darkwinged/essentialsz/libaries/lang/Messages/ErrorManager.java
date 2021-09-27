package me.darkwinged.essentialsz.libaries.lang.Messages;

import me.darkwinged.essentialsz.Main;

public class ErrorManager {

    private static final Main plugin = Main.getInstance;
    private static final String prefix = plugin.MessagesFile.getConfig().getString("Error.Prefix");

    public static String getErrors(Errors type) {
        String errors = "";
        switch (type) {
            case NoPermission:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoPermission");
                break;
            case Console:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.Console");
                break;
            case SpecifyPlayer:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.SpecifyPlayer");
                break;
            case NoPlayerFound:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoPlayerFound");
                break;
            case Cooldown:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.Cooldown");
                break;
            case CooldownItem:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.CooldownItem");
                break;
            case SenderInstaceOfPlayer:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.SenderInstaceOfPlayer");
                break;
            case DisabledCommand:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.DisabledCommand");
                break;

            case MessageEmpty:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.MessageEmpty");
                break;
            case DataFileError:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.DataFileError");
                break;

            case NoAccount:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoAccount");
                break;
            case InvalidAmount:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.InvalidAmount");
                break;
            case NotEnoughMoney:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NotEnoughMoney");
                break;
            case UsageEconomy:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.UsageEconomy");
                break;
            case UsagePay:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.UsagePay");
                break;
            case NoPlacePlayerHead:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoPlacePlayerHead");
                break;
            case NoPouch:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoPouch");
                break;
            case InvalidPouch:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.InvalidPouch");
                break;
            case SellUsage:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.SellUsage");
                break;
            case NoSellableItems:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoSellableItems");
                break;
            case Sellwand:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.Sellwand");
                break;

            case TPUsage:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.TPUsage");
                break;
            case TPhereUsage:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.TPhereUsage");
                break;
            case NoPreviousLocation:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoPreviousLocation");
                break;
            case NoWarpNameProvided:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoWarpNameProvided");
                break;
            case WarpDoesNotExist:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.WarpDoesNotExist");
                break;
            case WarpAlreadyExist:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.WarpAlreadyExist");
                break;
            case NoWarpsFound:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoWarpsFound");
                break;
            case NoHomeNameProvided:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoHomeNameProvided");
                break;
            case NoHomes:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoHomes");
                break;
            case HomeDoesNotExist:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.HomeDoesNotExist");
                break;
            case HomeAlreadyExist:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.HomeAlreadyExist");
                break;

            case GamemodeUsage:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.GamemodeUsage");
                break;
            case WorldGenUsage:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.WorldGenUsage");
                break;
            case InvalidWorld:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.InvalidWorld");
                break;
            case InvalidGameMode:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.InvalidGameMode");
                break;
            case FullFood:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.FullFood");
                break;
            case Length:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.Length");
                break;
            case WeatherType:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.WeatherType");
                break;
            case SpecifyTime:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.SpecifyTime");
                break;
            case SpecifyWorld:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.SpecifyWorld");
                break;
            case NoChest:
                errors = prefix + plugin.MessagesFile.getConfig().getString("Error.NoChest");
                break;
        }
        return plugin.essentialsZAPI.utils.chat(errors);
    }

}
