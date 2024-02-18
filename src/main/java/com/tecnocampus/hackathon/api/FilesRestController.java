package com.tecnocampus.hackathon.api;

import java.security.Principal;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tecnocampus.hackathon.application.FilesController;
import com.tecnocampus.hackathon.application.dto.FileRecordDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/files")
public class FilesRestController {
    
    private FilesController filesController;

    public FilesRestController(FilesController filesController) {
        this.filesController = filesController;
    }

    @PostMapping()
    public FileRecordDTO storeFile(Principal principal, 
            @RequestParam("file") MultipartFile file, 
            @RequestParam(value = "isPublic", defaultValue = "false") boolean isPublic, 
            @RequestParam(value = "whitelisted", defaultValue = "") List<String> whitelisted) {
        return filesController.storeFile(principal.getName(), file, isPublic, whitelisted);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id, Principal principal) {
        return filesController.getFile(id, principal.getName());
    }

}