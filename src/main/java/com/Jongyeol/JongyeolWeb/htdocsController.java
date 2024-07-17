package com.Jongyeol.JongyeolWeb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class htdocsController extends ControllerExtend {

    @GetMapping("/{String}")
    public ResponseEntity<Resource> string(HttpServletRequest request, @PathVariable("String") String string) throws IOException {
        info(request, string + " 파일을 불러오기 시도합니다");
        File file = new File(JSetting.instance.htdocsPath + "/" + string);
        boolean raw = new File(JSetting.instance.rawPath + "/" + string).exists();
        if(!file.exists()) {
            error(request, string + " 파일을 찾을 수 없습니다");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (file.isDirectory()) {
            file = new File(JSetting.instance.htdocsPath + "/" + string + "/index");
            raw = new File(JSetting.instance.rawPath + "/" + string + "/index").exists();
        }
        Path path = Paths.get(file.getPath());
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        if(!raw) headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        info(request, string + " 파일을 불러왔습니다.");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
