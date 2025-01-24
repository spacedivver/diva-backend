package com.apt.diva.analysis.controller;

import com.apt.diva.analysis.service.AnalysisService;
import com.apt.diva.expertanalysis.domain.response.ExpertAnalysisResponse;
import com.apt.diva.financial.domain.response.FinancialResponse;
import com.apt.diva.investmentmovement.domain.Response.InvestmentMovementResponse;
import com.apt.diva.macroeconomics.domain.response.MacroeconomicsResponse;
import com.apt.diva.newspolicy.domain.response.NewsPolicyResponse;
import com.apt.diva.report.domain.response.ReportResponse;
import com.apt.diva.source.domain.response.SourceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/analysis")
@Tag(name="분석 결과 api")
public class AnalysisController {
    private final AnalysisService analysisService;

    @Operation(summary = "재무 재표 관련 정보 조회")
    @GetMapping("/financial")
    public ResponseEntity<FinancialResponse> getFinancial(@RequestParam String stockCode) {
        FinancialResponse response = analysisService.getFinancial(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "거시 경제 관련 정보 조회")
    @GetMapping("/macroeconomic")
    public ResponseEntity<MacroeconomicsResponse> getMacroeconomic(@RequestParam String stockCode) {
        MacroeconomicsResponse response = analysisService.getMacroeconomic(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "정책 관련 정보 조회")
    @GetMapping("/news-policy")
    public ResponseEntity<NewsPolicyResponse> getNewsPolicy(@RequestParam String stockCode) {
        NewsPolicyResponse response = analysisService.getNewsPolicy(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "투자 동향 관련 정보 조회")
    @GetMapping("/investment-movement")
    public ResponseEntity<InvestmentMovementResponse> getInvestmentMovement(@RequestParam String stockCode) {
        InvestmentMovementResponse response = analysisService.getInvestmentMovement(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전문가 분석 관련 정보 조회")
    @GetMapping("/expert-analysis")
    public ResponseEntity<ExpertAnalysisResponse> getExpertAnalysis(@RequestParam String stockCode) {
         ExpertAnalysisResponse response = analysisService.getExpertAnalysis(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "출처 관련 정보 조회")
    @GetMapping("/source")
    public ResponseEntity<SourceResponse> getSource(@RequestParam String stockCode) {
        SourceResponse response=analysisService.getSource(stockCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "종합 분석 리포트 조회")
    @GetMapping("/report")
    public ResponseEntity<ReportResponse> getReport(@RequestParam String stockCode){
        ReportResponse response=analysisService.getReport(stockCode);
        return ResponseEntity.ok(response);
    }
}
