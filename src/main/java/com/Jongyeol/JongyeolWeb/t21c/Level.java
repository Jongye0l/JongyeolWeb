package com.Jongyeol.JongyeolWeb.t21c;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    public int id;
    public String song;
    public String artist;
    public String creator;
    public String diff;
    public String diffstrength;
    public String feeling;
    public String forum;
    public String vidLink;
    public String dlLink;
    public String workshopLink;
    public static List<Level> getLevels() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("https://t21c-be.run-us-west2.goorm.site/levels/", String.class);
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<Level> levels = new ArrayList<>();
        for (JsonElement jsonElement : array) {
            levels.add(gson.fromJson(jsonElement, Level.class));
        }
        return levels;
    }
}
