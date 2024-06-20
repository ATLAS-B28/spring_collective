package com.example.wells_forage.repository;

import com.example.wells_forage.dto.AdvisorDTO;
import com.example.wells_forage.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}
