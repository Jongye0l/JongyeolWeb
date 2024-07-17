package com.Jongyeol.JongyeolWeb;

import kr.jongyeol.jaServer.Settings;

import java.io.IOException;

public class JSetting extends Settings {
    public static JSetting instance;
    public String modFilePath;
    public String htdocsPath;
    public String rawPath;

    public static void load() throws IOException {
        Settings.load(JSetting.class);
        instance = (JSetting) Settings.instance;
    }
}
