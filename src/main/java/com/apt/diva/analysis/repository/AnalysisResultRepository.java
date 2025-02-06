package com.apt.diva.analysis.repository;

import com.apt.diva.analysis.domain.entity.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
    AnalysisResult findByAnalysisResultId(Long analysisResultId);
}
