package com.apt.diva.financial.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Financial {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="financial_id")
    private Long financialId;

    @Column(name="stock_code")
    private String stockCode;

    @Column(name="current_assets")
    private String currentAssets;

    @Column(name="non_current_assets")
    private String nonCurrentAssets;

    @Column(name="total_assets")
    private String totalAssets;

    @Column(name="current_liabilities")
    private String currentLiabilities;

    @Column(name="non_current_liabilities")
    private String nonCurrentLiabilities;

    @Column(name="total_liabilities")
    private String totalLiabilities;

    @Column(name="capital_stock")
    private String capitalStock;

    @Column(name="retained_earnings")
    private String retainedEarnings;

    @Column(name="total_equity")
    private String totalEquity;

    @Column(name="revenue")
    private String revenue;

    @Column(name="operating_income")
    private String operatingIncome;

    @Column(name="income_before_taxes")
    private String incomeBeforeTaxes;

    @Column(name="net_income")
    private String netIncome;

    @Column(name="net_income_less")
    private String netIncomeLess;

    @Column(name="comprehensive_income")
    private String comprehensiveIncome;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
