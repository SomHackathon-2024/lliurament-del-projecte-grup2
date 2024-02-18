package com.tecnocampus.hackathon.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tecnocampus.hackathon.application.dto.FileRecordDTO;
import com.tecnocampus.hackathon.domain.FileRecord;
import com.tecnocampus.hackathon.domain.User;
import com.tecnocampus.hackathon.exception.BadRequestException;
import com.tecnocampus.hackathon.exception.ForbiddenException;
import com.tecnocampus.hackathon.exception.notfound.EntityNotFound;
import com.tecnocampus.hackathon.persistence.FilesRepository;
import com.tecnocampus.hackathon.persistence.UserRepository;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Service
public class FilesController {
    private final Path fileStorageLocation;

    private FilesRepository filesRepository;
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public FilesController(FilesRepository filesRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.filesRepository = filesRepository;
        this.modelMapper = modelMapper;

        this.fileStorageLocation = Paths.get("alexandria/").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public ResponseEntity<Resource> getFile(String fileId, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ForbiddenException("User not found with username " + username));

        FileRecord fileRecord = filesRepository.findById(fileId)
            .orElseThrow(() -> new EntityNotFound(FileRecord.class, "File not found with id " + fileId));

        if (!fileRecord.isPublic() && !fileRecord.getWhitelist().contains(user))
            throw new ForbiddenException();

        Path filepath = Paths.get(fileRecord.getPath()).toAbsolutePath().normalize();
        Resource resource;
        try {
            resource = new org.springframework.core.io.UrlResource(filepath.toUri());
        } catch (IOException e) {
            throw new RuntimeException("File not found with id " + fileId);
        }

        return ResponseEntity.ok()
            .header(CONTENT_DISPOSITION, "attachment; filename=\"" + fileRecord.getName() + "\"")
            .body(resource);
    }

    public FileRecordDTO storeFile(String username, MultipartFile file, boolean isPublic, List<String> whitelisted) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ForbiddenException());

        if (file.isEmpty())
            throw new BadRequestException("Failed to store empty file.");

        FileRecord fileRecord = new FileRecord();
        fileRecord.setUploader(user);
        fileRecord.getWhitelist().add(user);
        fileRecord.setPublic(isPublic);

        for (String usernameWhitelisted : whitelisted) {
            User userWhitelisted = userRepository.findByUsername(usernameWhitelisted)
                .orElseThrow(() -> new BadRequestException("User " + usernameWhitelisted + " not found."));
            fileRecord.getWhitelist().add(userWhitelisted);
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        fileRecord.setName(filename);
        fileRecord.setSize(file.getSize());
        fileRecord.setType(file.getContentType());
        
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileRecord.getId());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            fileRecord.setPath(targetLocation.toString());

        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + filename + ". Please try again!");
        }

        filesRepository.save(fileRecord);

        return modelMapper.map(fileRecord, FileRecordDTO.class);
    }
}