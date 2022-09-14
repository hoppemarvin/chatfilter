package de.colddev.chatfilter;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class ChatFilter extends Plugin {

    /**
     *
     */
    @Getter private static ChatFilter instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
    }
}
