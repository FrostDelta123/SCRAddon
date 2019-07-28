package ru.frostdelta.scraddon;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DputCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 5 && args[0].equalsIgnoreCase("put")){
            if(sender.hasPermission("cartr.admin.put")){
                Network.handle(args[1], args[2], args[3], args[4]);
            }else sender.sendMessage(ChatColor.RED + "У вас нет прав!");
        }else sender.sendMessage(ChatColor.RED + "Неправильное использование команды!");
        return true;
    }
}
