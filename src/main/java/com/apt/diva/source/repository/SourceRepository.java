package com.apt.diva.source.repository;

import com.apt.diva.source.domain.entity.Category;
import com.apt.diva.source.domain.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findByAnalysisResultIdAndCategory(Long analysisResultId, Category category);

}
