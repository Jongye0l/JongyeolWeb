package com.Jongyeol.JongyeolWeb.t21c;

import com.google.gson.*;
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
    public String ytId;
    private void update() {
        if(feeling != null) feeling = (feeling * 100) / 100;
        creator = creator.replaceAll("\\n", " ");
        diffString = levelString(diff);
        feelingString = levelString(feeling);
        forumString = levelString(forum);
        ytId = vidLink.replace("https://youtu.be/", "").replace("https://www.youtube.com/watch?", "")
                .replace("https://www.youtube.com/shorts/", "").replace("-5", "")
                .replace("https://www.youtube.com/playlist?list=", "");
        if(!vidLink.contains("https://youtu.be/") && !vidLink.contains("https://www.youtube.com/watch?") && !vidLink.equals("-5")
                && !vidLink.contains("https://www.youtube.com/shorts/") && !vidLink.contains("https://www.youtube.com/playlist?list=")) {
            System.out.println("[Id #" + id + "] " + vidLink);
        }
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
        String json = restTemplate.getForObject("https://be.t21c.kro.kr/levels", String.class);
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
    public static Level getLevel(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("https://be.t21c.kro.kr/levels/" + id, String.class);
        JsonElement element = JsonParser.parseString(json);
        Gson gson = new Gson();
        Level level = gson.fromJson(element, Level.class);
        level.update();
        return level;
    }
}
