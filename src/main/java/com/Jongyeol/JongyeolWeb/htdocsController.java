package com.Jongyeol.JongyeolWeb;

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
public class htdocsController {
    @GetMapping("/{String}")
    public ResponseEntity<Resource> string(@PathVariable("String") String string) throws IOException {
        System.out.println("Trying to get Resource " + string);
        File file = new File("htdocs/" + string);
        boolean raw = new File("raw/" + string).exists();
        if(!file.exists()) {
            System.out.println(string + " is not exist");
            file = new File("FileNotFound");
            raw = true;
        } else if (file.isDirectory()) {
            file = new File("htdocs/" + string + "/index");
            raw = new File("raw/" + string + "/index").exists();
        }
        Path path = Paths.get(file.getPath());
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        if(!raw) headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
