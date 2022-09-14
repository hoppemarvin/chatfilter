package de.colddev.chatfilter.util;

import lombok.*;

@AllArgsConstructor
@Getter
public class SwearEntry {

    private String content;
    private int level;

}
