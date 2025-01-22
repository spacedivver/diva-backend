package com.apt.diva.macroeconomics.repository;

import com.apt.diva.macroeconomics.domain.entity.Macroeconomics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacroeconomicRepository extends JpaRepository<Macroeconomics, Long> {
    Macroeconomics findByMacroeconomicsId(Long macroeconomicId);
}
