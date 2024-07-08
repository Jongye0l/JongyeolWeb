package com.Jongyeol.JongyeolWeb;

import kr.jongyeol.jaServer.Server;
import kr.jongyeol.jaServer.Settings;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Settings.load(Settings.class);
        Server.main(null);
    }
}
