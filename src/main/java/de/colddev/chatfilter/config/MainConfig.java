package de.colddev.chatfilter.config;

import de.colddev.chatfilter.util.GroupEntry;
import de.colddev.chatfilter.util.SwearEntry;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainConfig {

    private List<SwearEntry> swearEntries = Arrays.asList(new SwearEntry("Jude", 3), new SwearEntry("Hurensohn", 2), new SwearEntry("Opfer", 1));
    private List<GroupEntry> serverGroups = Arrays.asList(new GroupEntry("Admin", "server.admin", 9, "§4"));

}
