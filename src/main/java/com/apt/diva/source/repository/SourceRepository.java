package com.apt.diva.source.repository;

import com.apt.diva.source.domain.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<Source, Long> {
    Source findBySourceId(Long sourceId);
}
