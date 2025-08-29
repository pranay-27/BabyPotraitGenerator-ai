package com.baby.potrait.generator.ai.service;

import com.baby.potrait.generator.ai.entity.Portrait;
import com.baby.potrait.generator.ai.repository.PortraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortraitService {

    private final PortraitRepository portraitRepository;

    public PortraitService(PortraitRepository portraitRepository) {
        this.portraitRepository = portraitRepository;
    }

    public Portrait savePortrait(Portrait portrait) {
        return portraitRepository.save(portrait);
    }

    public List<Portrait> getPortraitsByUser(Long id) {
        return portraitRepository.findByUser(id);
    }

    public List<Portrait> getAllPortraits() {
        return portraitRepository.findAll();
    }

    public Optional<Portrait> getPortraitById(Long id) {
        return portraitRepository.findById(id);
    }
}
