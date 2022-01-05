package review.review;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {
    Review main;

    public CommandReload(Review plugin) {
        main = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Если из консоли то тогда релоадит. Нужно чтобы в консоле не было ошибки
        if (sender instanceof ConsoleCommandSender == true) {
            main.reloadConfig();
            sender.sendMessage(String.valueOf(main.getConfig().getString("reload")));
        }else {
            //Теперь если игрок
            Player executor = (Player) sender;
            //Проверка если ли у игрока pex

                                //Если нет тогда
            if (executor.hasPermission("review.reload") == false) {
                executor.sendMessage(String.valueOf(main.getConfig().getString("noperms")));
                return true;
            }

                                //Если есть тогда
            main.reloadConfig();
            sender.sendMessage(String.valueOf(main.getConfig().getString("reload")));
            return true;
        }
        return true;
   }
}