package de.colddev.chatfilter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class ConfigLoader {

    private static final JsonParser parser = new JsonParser();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T loadConfig(Class<T> clazz, File file) throws IOException {
        if (file.createNewFile()) {
            String json = null;
            try {
                json = gson.toJson(parser.parse(gson.toJson(clazz.newInstance())));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            try (PrintWriter out = new PrintWriter(file)) {
                out.println(json);
            }
        }
        return gson.fromJson(new String(Files.readAllBytes(file.toPath())), clazz);
    }

    public static void saveConfig(Object config, File file) throws IOException {
        if (file.createNewFile()) {
            String json = gson.toJson(parser.parse(gson.toJson(config)));
            try (PrintWriter out = new PrintWriter(file)) {
                out.println(json);
            }
        }
    }
}
