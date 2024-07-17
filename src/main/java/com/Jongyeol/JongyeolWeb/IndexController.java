package com.Jongyeol.JongyeolWeb;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController extends ControllerExtend {
    @GetMapping("/")
    public String mainMenu(HttpServletRequest request) {
        info(request, "메인 화면을 불러왔습니다");
        return "<h1>집에가고싶다</h1>";
    }
}
