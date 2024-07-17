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

@RestController
@RequestMapping("/resourcePack")
public class ResourcePackController extends ControllerExtend {
    @GetMapping("/Jipper/{String}")
    public ResponseEntity<Resource> jipper(HttpServletRequest request, @PathVariable("String") String string) throws IOException {
        Path path = new File("resourcePack/JipperRpg.zip").toPath();
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        info(request, "리소스팩을 다운로드합니다.(uuid: " + string + ")");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
