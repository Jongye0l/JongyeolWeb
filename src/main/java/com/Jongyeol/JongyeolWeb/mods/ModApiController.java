package com.Jongyeol.JongyeolWeb.mods;

import com.Jongyeol.JongyeolWeb.ControllerExtend;
import com.Jongyeol.JongyeolWeb.JSetting;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.jongyeol.jaServer.data.ModData;
import kr.jongyeol.jaServer.data.Version;
import org.springframework.core.io.ByteArrayResource;
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

@RestController
@RequestMapping("mods/api")
public class ModApiController extends ControllerExtend {
    @GetMapping("/{modName}/Repository.json")
    public String getModRepository(HttpServletRequest request, HttpServletResponse response, @PathVariable String modName) {
        ModData modData = ModData.getModData(modName);
        if(modData == null) {
            error(request, modName + " 모드를 찾을 수 없어 레포지토리를 불러오지 못했습니다.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Mod Not Founded";
        }
        JsonObject modObject = new JsonObject();
        modObject.addProperty("Id", modData.getName());
        modObject.addProperty("Version", modData.getVersion().toString());
        modObject.addProperty("DownloadUrl", modData.getDownloadLink().getLink(modData.getVersion()));
        JsonArray modArray = new JsonArray();
        modArray.add(modObject);
        JsonObject object = new JsonObject();
        object.add("Releases", modArray);
        return object.toString();
    }

    @GetMapping("/download/{modName}/{version}")
    public ResponseEntity<Resource> string(HttpServletRequest request, @PathVariable String modName, @PathVariable String version) throws IOException {
        ModData modData = ModData.getModData(modName);
        if(modData == null) {
            error(request, modName + " 모드를 찾을 수 없어 다운로드를 실패했습니다.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Version ver = version.equals("latest") ? modData.getVersion() : new Version(version);
        File file = new File(JSetting.instance.modFilePath + "/" + modName + "/" + ver.toString() + ".zip");
        HttpHeaders headers = new HttpHeaders();
        if(!file.exists()) {
            String url = modData.getDownloadLink().getLink(ver);
            if(url == null) {
                error(request, modName + " 모드의 " + ver + " 버전을 찾을 수 없어 다운로드를 실패했습니다.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            headers.add(HttpHeaders.LOCATION, url);
            info(request, modName + " 모드의 " + ver + " 버전으로 리다이렉트 합니다");
            return new ResponseEntity<>(new ByteArrayResource(new byte[0]), headers, HttpStatus.OK);
        }
        Path path = Paths.get(file.getPath());
        String contentType = Files.probeContentType(path);
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + modData.getName() + ".zip");
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        info(request, modName + " 모드의 " + ver + " 버전을 다운로드합니다.");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
