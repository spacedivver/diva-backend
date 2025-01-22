package com.apt.diva.investmentmovement.repository;

import com.apt.diva.investmentmovement.domain.entity.InvestmentMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentMovementRepository extends JpaRepository<InvestmentMovement, Long> {
    InvestmentMovement findByInvestmentMovementId(Long investmentMovementId);
}
