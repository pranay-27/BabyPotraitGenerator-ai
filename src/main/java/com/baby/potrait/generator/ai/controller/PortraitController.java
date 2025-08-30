package com.baby.potrait.generator.ai.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baby.potrait.generator.ai.entity.Portrait;
import com.baby.potrait.generator.ai.entity.Style;
import com.baby.potrait.generator.ai.entity.User;
import com.baby.potrait.generator.ai.service.PortraitService;
import com.baby.potrait.generator.ai.service.StyleService;
import com.baby.potrait.generator.ai.service.UserService;

@RestController
@RequestMapping("/api")
public class PortraitController {

    private final PortraitService portraitService;
    private final UserService userService;
    private final StyleService styleService;

    public PortraitController(PortraitService portraitService, UserService userService, StyleService styleService) {
        this.portraitService = portraitService;
        this.userService = userService;
        this.styleService = styleService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Portrait> uploadBabyPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        Portrait portrait = portraitService.uploadBabyPhoto(file);
        return ResponseEntity.ok(portrait);
    }

    @GetMapping("portrait/{id}")
    public ResponseEntity<Portrait>getPortraitById(@PathVariable Long id) {
        Portrait portrait = portraitService.getPortraitById(id)
                            .orElseThrow(() -> new RuntimeException("Portrait not found by id "+id));
        
        return ResponseEntity.ok(portrait);
    }

    @PostMapping("/generate")
    public Portrait generatePortrait(@RequestParam Long userId,
                                     @RequestParam Long styleId,
                                     @RequestParam String uploadedFileUrl,
                                     @RequestParam String uploadedFileName) throws Exception {

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Style style = styleService.getStyleById(styleId)
                .orElseThrow(() -> new RuntimeException("Style not found"));
        
        return portraitService.generatePortrait(user, style, uploadedFileUrl, uploadedFileName);
    }
}