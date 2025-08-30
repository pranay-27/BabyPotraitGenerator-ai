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
import com.baby.potrait.generator.ai.service.PortraitService;

@RestController
@RequestMapping("/api")
public class PortraitController {

    private final PortraitService portraitService;

    public PortraitController(PortraitService portraitService) {
        this.portraitService = portraitService;
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
}