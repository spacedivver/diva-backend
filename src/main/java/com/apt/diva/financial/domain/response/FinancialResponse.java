package com.apt.diva.financial.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinancialResponse {

    private String stockCode;
    private String currentAssets;
    private String nonCurrentAssets;
    private String totalAssets;
    private String currentLiabilities;
    private String nonCurrentLiabilities;
    private String totalLiabilities;
    private String capitalStock;
    private String retainedEarnings;
    private String totalEquity;
    private String revenue;
    private String operatingIncome;
    private String incomeBeforeTaxes;
    private String netIncome;
    private String netIncomeLess;
    private String comprehensiveIncome;

}
