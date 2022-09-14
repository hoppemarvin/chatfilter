package de.colddev.chatfilter.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class BanEntry {

    private UUID uuid;
    private Timestamp timestamp;
    private String evidence;
    private SwearEntry swearEntry;
}
