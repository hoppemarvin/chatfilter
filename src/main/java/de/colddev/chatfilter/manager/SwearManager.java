package de.colddev.chatfilter.manager;

import de.colddev.chatfilter.ChatFilter;
import de.colddev.chatfilter.util.SwearEntry;

import java.util.*;

public class SwearManager extends Manager {

    private int largestWordLength = 0;
    private static Map<String, SwearEntry> swears = new HashMap<String, SwearEntry>();

    @Override
    void enable() {
        int readCounter = 0;
        for(SwearEntry entry : ChatFilter.getInstance().getMainConfig().getSwearEntries()){
            if(entry.getContent().length() > largestWordLength){
                largestWordLength= entry.getContent().length();
            }
            swears.put(entry.getContent().replaceAll(" ", "").toLowerCase(), entry);
            readCounter++;
        }
        System.out.println("Loaded "+readCounter+" swears");
    }

    public SwearEntry isSwear(String message) {
        if (message == null) return null;

        String input = message;
        input = input.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").
                replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");

        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        for (int i = 0; i < input.length(); i++) {
            for (int offset = 1; offset < (input.length() + 1 - i) && offset < largestWordLength; offset++) {
                String wordToCheck = input.substring(i, i + offset);
                if (swears.containsKey(wordToCheck)) {
                    boolean ignore = false;
                    return swears.get(wordToCheck);
                }
            }
        }
        return null;
    }
}
