package com.apt.diva.expertanalysis.repository;

import com.apt.diva.expertanalysis.domain.entity.ExpertAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertAnalysisRepository extends JpaRepository<ExpertAnalysis, Long> {
    ExpertAnalysis findByExpertAnalysisId(Long expertAnalysisId);
}
