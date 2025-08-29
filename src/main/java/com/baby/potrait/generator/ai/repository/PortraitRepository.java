package com.baby.potrait.generator.ai.repository;

import com.baby.potrait.generator.ai.entity.Portrait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortraitRepository extends JpaRepository<Portrait,Long> {

    List<Portrait> findByUser(Long Id);
}
