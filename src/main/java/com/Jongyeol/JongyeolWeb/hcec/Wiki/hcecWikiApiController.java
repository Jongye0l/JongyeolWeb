package com.Jongyeol.JongyeolWeb.hcec.Wiki;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

@RestController
@RequestMapping("/hcec/api/wiki")
public class hcecWikiApiController {

    private final WikiPageRepository repository = new FileWikiPageRepository();

    @GetMapping("/{title}")
    public WikiData getPage(@PathVariable String title) {
        WikiData page = repository.findByTitle(title);
        if (page == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Page not found");
        }
        return page;
    }

    @PostMapping
    public WikiData savePage(@RequestBody WikiData page) {
        return repository.save(page);
    }

    @DeleteMapping("/{title}")
    public void deletePage(@PathVariable String title) {
        File file = new File(FileWikiPageRepository.storageDir + "/" + title + ".txt");
        if (!file.delete()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete page");
        }
    }
}
