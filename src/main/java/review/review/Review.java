package review.review;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Review extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    @EventHandler(priority = EventPriority.HIGH)
    public void onEnable() {
        // Создание папки плагина в plugins
        File file = new File("plugins/Review");
        file.mkdir();

        // Создание файла где будут храниться отзывы
        File review = new File("plugins/Review/review.yml");
        if (!review.exists()) {
            try {
                // Если его не существует, то он создаётся
                review.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //создаём дефолт конфиг
        config.addDefault("usage", "Используйте: /review или /rv <Отзыв/Предложение>");
        config.addDefault("ready", "Ваше сообщение отправлено. Спасибо что помогаете улучшить сервер.");
        config.addDefault("reload", "Конфиг перезагружен.");
        config.addDefault("noperms", "У вас нету прав на выполнение данной комманды.");
        config.addDefault("topline", "============================================");
        config.addDefault("player", "Игрок: ");
        config.addDefault("date", "Дата: ");
        config.addDefault("review", "Отзыв: ");
        config.addDefault("bottomline", "============================================");
        config.options().copyDefaults(true);
        saveConfig();

        //получение комманд из других классов
        getCommand("review").setExecutor(new CommandReview(this));
        getCommand("reviewreload").setExecutor(new CommandReload(this));

        //Сообщение в консоль что плагин запущен
        getLogger().info(ChatColor.AQUA + "Review plugin is load");
    }

    @Override
    public void onDisable() {
        //Сообщение в консоль что плагин остановлен
        getLogger().info(ChatColor.AQUA + "Review plugin is stop");
    }
}
