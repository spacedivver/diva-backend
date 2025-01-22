package com.apt.diva.financial.repository;

import com.apt.diva.financial.domain.entity.Financial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinancialRepository extends JpaRepository<Financial, Long> {
    Financial findByStockCode(String stockCode);
}
