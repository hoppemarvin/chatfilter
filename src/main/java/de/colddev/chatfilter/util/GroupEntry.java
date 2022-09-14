package de.colddev.chatfilter.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupEntry {

    private String name;
    private String permission;
    private int priority;
    private String displayChat;
}
