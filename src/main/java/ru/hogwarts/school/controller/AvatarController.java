package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")

public class AvatarController {
    private AvatarService avatarService;
    private AvatarRepository avatarRepository;

    public AvatarController(AvatarService avatarService, AvatarRepository avatarRepository) {
        this.avatarService = avatarService;
        this.avatarRepository = avatarRepository;
    }

    @PostMapping(value = "/{Id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long Id, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(Id, avatar);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{Id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long Id) {

        Avatar avatar = avatarService.findAvatar(Id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());

    }

    @GetMapping(value = "/{Id}/avatar")
    public void downloadAvatar(@PathVariable Long Id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(Id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }


    // Дз № 1 по базам данных
    @GetMapping
    public Page<Avatar> getAvatars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        return avatarRepository.findAll(pageable);
    }
}
