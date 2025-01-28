package com.apt.diva.stock.repository;

import com.apt.diva.stock.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByStockCode(String stockCode);
}
