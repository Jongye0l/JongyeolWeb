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
    public Float diff;
    public String diffString;
    public String diffstrength;
    public Float feeling;
    public String feelingString;
    public Float forum;
    public String forumString;
    public String vidLink;
    public String dlLink;
    public String workshopLink;
    private void update() {
        if(feeling != null) feeling = (feeling * 100) / 100;
        creator = creator.replaceAll("\\n", " ");
        diffString = levelString(diff);
        feelingString = levelString(feeling);
        forumString = levelString(forum);
    }
    private String levelString(Float diff) {
        if(diff == null) return "";
        if(diff == 21) return "21";
        if(diff > 21.04 && diff < 21.06) return "21+"; // 2진수 이슈 tlqkf
        if(diff >= 20) {
            if(diff * 10 % 1 >= 0.5) return Math.floor(diff * 10) / 10 + "+";
            return diff + "";
        }
        else if(diff > 18 && diff % 1 <= 0.5) return diff.intValue() + "+";
        return diff.intValue() + "";
    }
    public static List<Level> getLevels() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("https://be.t21c-adofai.kro.kr/levels", String.class);
        JsonArray array = JsonParser.parseString(json).getAsJsonObject().get("results").getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<Level> levels = new ArrayList<>();
        for (JsonElement jsonElement : array) {
            Level level = gson.fromJson(jsonElement, Level.class);
            level.update();
            levels.add(level);
        }
        return levels;
    }
}
