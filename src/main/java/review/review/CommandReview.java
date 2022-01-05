package review.review;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandReview implements CommandExecutor {

    private Review plugin;

    public CommandReview(Review plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //Получение даты и времени которое сейчас
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        //Аргументы комманды в текст
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            text.append(args[i] + " ");
        }

        // получение файла с отзывами
        File review = new File("plugins/Review/review.yml");
        FileConfiguration reviews = YamlConfiguration.loadConfiguration(review);
        //Создание строки с отзывами
        List<String> list = reviews.getStringList("review");
        //Если файлл пустой то создаёт строку
        if (list.isEmpty()) list = new ArrayList<String>();
            //Проверка если ли сообщение
            if (args.length <= 0) {
                sender.sendMessage(String.valueOf(plugin.getConfig().getString("usage")));
                return false;
            } else {
                //Если комманда выполняется из консоли
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage("You are not Player");
                    return false;
                }
                Player p = (Player)sender;
                //Добавление отзыва в rewiew.yml

                list.add(String.valueOf(plugin.getConfig().getString("topline")));
                list.add(String.valueOf(plugin.getConfig().getString("player")) + p.getName());
                list.add(String.valueOf(plugin.getConfig().getString("date")) + format.format(now));
                list.add(String.valueOf(plugin.getConfig().getString("review")) + String.valueOf(text));
                list.add(String.valueOf(plugin.getConfig().getString("bottomline")));

                //ответ что отзыв успешно отправился
                sender.sendMessage(String.valueOf(plugin.getConfig().getString("ready")));

                //сохраниение
                reviews.set("review", list);
                try {
                    reviews.save(review);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        return true;
    }
}