package com.apt.diva.analysis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="analysis_result_id")
    private Long analysisResultId;

    @Column(name="stock_id")
    private Long stockId;

    @Column(name="financial_id")
    private Long financialId;

    @Column(name="news_id")
    private Long newsId;

//    @Column(name="investment_movement_id")
//    private Long investmentMovementId;

    @Column(name="expert_analysis_id")
    private Long expertAnalysisId;

    @Column(name="report_id")
    private Long reportId;

//    @Column(name="source_id")
//    private Long sourceId;

//    @Column(name="macroeconomics_id")
//    private Long macroeconomicsId;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
