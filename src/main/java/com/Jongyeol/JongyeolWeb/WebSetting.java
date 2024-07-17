package com.Jongyeol.JongyeolWeb;

import kr.jongyeol.jaServer.Settings;

public class WebSetting extends Settings {
    public static WebSetting instance;
    public String modFilePath;
    public String htdocsPath;
    public String rawPath;

    public WebSetting() {
        instance = this;
    }
}
