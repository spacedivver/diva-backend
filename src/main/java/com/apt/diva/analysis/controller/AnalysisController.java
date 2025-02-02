package com.apt.diva.analysis.controller;

import com.apt.diva.analysis.service.AnalysisService;
import com.apt.diva.expertanalysis.domain.response.ExpertAnalysisResponse;
import com.apt.diva.financial.domain.response.FinancialResponse;
import com.apt.diva.investmentmovement.domain.Response.InvestmentMovementResponse;
import com.apt.diva.macroeconomics.domain.response.MacroeconomicsResponse;
import com.apt.diva.news.domain.response.NewsResponse;
import com.apt.diva.report.domain.response.ReportResponse;
import com.apt.diva.source.domain.response.SourceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/financial/{stockCode}")
    public ResponseEntity<FinancialResponse> getFinancial(@PathVariable String stockCode) {

        FinancialResponse response = analysisService.getFinancial(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "거시 경제, 정책 관련 정보 조회")
    @PostMapping("/macroeconomics/{stockCode}")
    public ResponseEntity<MacroeconomicsResponse> getMacroeconomics(@PathVariable String stockCode) {

        MacroeconomicsResponse response = analysisService.getMacroeconomics(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "시장 심리 및 투자 동향 관련 정보 조회")
    @PostMapping("/investment-movement/{stockCode}")
    public ResponseEntity<InvestmentMovementResponse> getInvestmentMovement(@PathVariable String stockCode) {

        InvestmentMovementResponse response = analysisService.getInvestmentMovement(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전문가 분석 관련 정보 조회")
    @PostMapping("/expert-analysis/{stockCode}")
    public ResponseEntity<ExpertAnalysisResponse> getExpertAnalysis(@PathVariable String stockCode) {

        ExpertAnalysisResponse response = analysisService.getExpertAnalysis(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "뉴스 관련 정보 조회")
    @PostMapping("/news/{stockCode}")
    public ResponseEntity<NewsResponse> getNewsPolicy(@RequestParam String stockCode) {

        NewsResponse response = analysisService.getNews(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "출처 관련 정보 조회")
    @PostMapping("/source/{stockCode}")
    public ResponseEntity<SourceResponse> getSource(@RequestParam String stockCode) {

        SourceResponse response=analysisService.getSource(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "종합 분석 리포트 조회")
    @PostMapping("/report/{stockCode}")
    public ResponseEntity<ReportResponse> getReport(@RequestParam String stockCode){

        ReportResponse response=analysisService.getReport(stockCode);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("OK");
        }

        return ResponseEntity.ok(response);
    }
}
