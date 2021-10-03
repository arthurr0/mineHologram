package pl.minecodes.minehologram.color;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final Pattern hexPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private ColorUtil() {
    }

    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(implementColors(message)));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(implementColors(message));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(implementColors(message));
    }

    public static void broadcastMessage(String message) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            sendMessage(onlinePlayer, message);
        }
    }

    public static void broadcastMessage(String message, String permission) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasPermission(permission)) {
                sendMessage(onlinePlayer, message);
            }
        }
    }

    public static String locationToMessage(Location location) {
        return "x: " + location.getBlockX() + " y: " + location.getBlockY() + " z: " + location.getBlockZ();
    }

    public static String implementColors(String message) {
        if (message == null) {
            return null;
        }
        for (Matcher matcher = hexPattern.matcher(message); matcher.find(); matcher = hexPattern.matcher(message)) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, ChatColor.of(color) + "");
        }

        return ChatColor.translateAlternateColorCodes('&', message.replace("<<", "«").replace(">>", "»"));
    }

    public static List<String> implementColors(List<String> message) {
        if (message == null) {
            return null;
        }
        List<String> messages = new ArrayList<>();
        message.forEach(s -> messages.add(implementColors(s)));
        return messages;
    }

    public static String removeColor(String message) {
        return ChatColor.stripColor(message);
    }

}
