package de.colddev.chatfilter;

import de.colddev.chatfilter.config.ConfigLoader;
import de.colddev.chatfilter.config.MainConfig;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ChatFilter extends Plugin {

    @Getter
    private static ChatFilter instance;

    @Getter private MainConfig mainConfig;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        if (!getDataFolder().exists() && !getDataFolder().mkdirs()) {
            System.out.println("Could not create Plugin Folder");
            System.exit(-1);
        }
        try {
            this.mainConfig = ConfigLoader.loadConfig(MainConfig.class, new File(this.getDataFolder() + "/config.json"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load Plugin Config");
            System.exit(-1);
        }
    }
}
