package de.colddev.chatfilter.listener;

import de.colddev.chatfilter.ChatFilter;
import de.colddev.chatfilter.config.ConfigLoader;
import de.colddev.chatfilter.util.BanEntry;
import de.colddev.chatfilter.util.SwearEntry;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerListener implements Listener {

    @EventHandler
    public void on(ChatEvent e){
        if(!(e.getSender() instanceof ProxiedPlayer))return;
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();

        BanEntry banEntry = ChatFilter.getInstance().getSwearManager().isBanned(p.getUniqueId());
        if(banEntry != null){
            e.setCancelled(true);
            messageBan(p,banEntry);
            return;
        }

        SwearEntry entry = ChatFilter.getInstance().getSwearManager().isSwear(e.getMessage());
        if(entry == null)return;
        e.setCancelled(true);
        p.sendMessage(new TextComponent("§cBitte achte auf deine Wortwahl."));

        if(entry.getLevel() == 3){
            banEntry = new BanEntry(p.getUniqueId(), new Timestamp(System.currentTimeMillis()), e.getMessage(), entry);
            messageBan(p,banEntry);
            ChatFilter.getInstance().getMainConfig().getBans().add(banEntry);
            try {
                ConfigLoader.saveConfig(ChatFilter.getInstance().getMainConfig(), new File(ChatFilter.getInstance().getDataFolder() + "/config.json"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void messageBan(ProxiedPlayer p, BanEntry banEntry){
        p.sendMessage(new TextComponent("§cDu wurdest für §e30 Minuten §caus dem Chat gebannt."));
        p.sendMessage(new TextComponent("§4Grund: §eunangemessenes Chatverhalten"));
        p.sendMessage(new TextComponent("§7Nachricht: §8"+banEntry.getEvidence()));
        p.sendMessage(new TextComponent("§cVerbleibende Zeit: §e"+new SimpleDateFormat("HH:mm:ss").format(new Date(banEntry.getTimestamp().getTime()))));
    }
}
