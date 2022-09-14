package de.colddev.chatfilter.util;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SwearEntry {

    @Getter private String content;
    @Getter private int level;

}
