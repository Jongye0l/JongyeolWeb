package com.Jongyeol.JongyeolWeb;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/JipperOverlayer")
public class JipperOverlayerController extends ControllerExtend {
    private static final Pattern pattern = Pattern.compile("%\\{Multiple:(\\d+)}%");
    private static final List<String> OLDER = List.of("1", "old", "older", "before");
    private static final List<String> DEFAULT = List.of("2", "default", "current", "this");
    private static final List<String> BETA = List.of("3", "beta", "test");
    private static final List<String> ALPHA = List.of("4", "alpha", "feature");

    @GetMapping("/Texts/{String}")
    public String textJson(HttpServletRequest request, @PathVariable("String") String string) throws IOException {
        String data = new String(Files.readAllBytes(Path.of("JipperOverlayer/Texts.json")));
        double i = 1;
        try {
            i = Double.parseDouble(string);
        } catch (NumberFormatException ignored) {
        }
        Matcher matcher = pattern.matcher(data);
        StringBuilder result = new StringBuilder();
        while(matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            double newValue = i * value;
            matcher.appendReplacement(result, Double.toString(newValue));
        }
        matcher.appendTail(result);
        info(request, "지퍼 리소스팩 텍스트를 받아옵니다.(배수: " + string + ")");
        return result.toString();
    }

    @GetMapping("/version")
    public String version(HttpServletRequest request) throws IOException {
        info(request, "지퍼 리소스팩 버전을 받아옵니다.");
        return Files.readString(Path.of("JipperOverlayer/version"));
    }

    @GetMapping("/Overlayer/{String}")
    public ResponseEntity<Resource> overlayer(HttpServletRequest request, @PathVariable("String") String string) throws IOException {
        File file = null;
        if(OLDER.contains(string)) file = new File("JipperOverlayer/Overlayer/older.zip");
        if(DEFAULT.contains(string)) file = new File("JipperOverlayer/Overlayer/default.zip");
        if(BETA.contains(string)) file = new File("JipperOverlayer/Overlayer/beta.zip");
        if(ALPHA.contains(string)) file = new File("JipperOverlayer/Overlayer/alpha.zip");
        if(file == null) {
            error(request, "지퍼 리소스팩의 " + string + "은 찾을 수 없습니다.");
            file = new File("JipperOverlayer/Overlayer/default.zip");
        }
        Path path = Paths.get(file.getPath());
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        info(request, "지퍼 리소스팩 파일을 다운로드 하였습니다.(Version: " + string + ")");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
