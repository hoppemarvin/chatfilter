package de.colddev.chatfilter.config;

import de.colddev.chatfilter.util.SwearEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainConfig {

    private List<SwearEntry> swearEntries = Arrays.asList(new SwearEntry("Jude", 3), new SwearEntry("Hurensohn", 2), new SwearEntry("Opfer", 1));

}
