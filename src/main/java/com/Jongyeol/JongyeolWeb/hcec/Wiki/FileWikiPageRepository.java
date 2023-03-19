package com.Jongyeol.JongyeolWeb.hcec.Wiki;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileWikiPageRepository implements WikiPageRepository {
    public static final String storageDir = "hcec/data/wiki";

    @Override
    public WikiData save(WikiData page) {
        String fileName = storageDir + "/" + page.getTitle() + ".txt";
        try {
            // 파일에 저장
            FileUtils.writeStringToFile(new File(fileName), page.getContent(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // 파일 저장에 실패한 경우 예외 처리
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public WikiData findByTitle(String title) {
        String fileName = storageDir + "/" + title + ".txt";
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            // 파일에서 읽어오기
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            WikiData page = new WikiData();
            page.setTitle(title);
            page.setContent(content);
            return page;
        } catch (IOException e) {
            // 파일 읽기에 실패한 경우 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}
