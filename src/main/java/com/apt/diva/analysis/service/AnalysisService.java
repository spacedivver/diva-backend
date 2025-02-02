package com.apt.diva.analysis.service;

import com.apt.diva.analysis.domain.entity.AnalysisResult;
import com.apt.diva.analysis.repository.AnalysisResultRepository;
import com.apt.diva.expertanalysis.domain.entity.ExpertAnalysis;
import com.apt.diva.expertanalysis.domain.response.ExpertAnalysisResponse;
import com.apt.diva.expertanalysis.repository.ExpertAnalysisRepository;
import com.apt.diva.financial.domain.entity.Financial;
import com.apt.diva.financial.domain.response.FinancialResponse;
import com.apt.diva.financial.repository.FinancialRepository;
import com.apt.diva.investmentmovement.domain.Response.InvestmentMovementResponse;
import com.apt.diva.investmentmovement.domain.entity.InvestmentMovement;
import com.apt.diva.investmentmovement.repository.InvestmentMovementRepository;
import com.apt.diva.macroeconomics.domain.entity.Macroeconomics;
import com.apt.diva.macroeconomics.domain.response.MacroeconomicsResponse;
import com.apt.diva.macroeconomics.repository.MacroeconomicRepository;
import com.apt.diva.news.domain.entity.News;
import com.apt.diva.news.domain.response.NewsResponse;
import com.apt.diva.news.repository.NewsRepository;
import com.apt.diva.report.domain.entity.Report;
import com.apt.diva.report.domain.response.ReportResponse;
import com.apt.diva.report.repository.ReportRepository;
import com.apt.diva.source.domain.entity.Source;
import com.apt.diva.source.domain.response.SourceResponse;
import com.apt.diva.source.repository.SourceRepository;
import com.apt.diva.stock.domain.request.AIRequest;
import com.apt.diva.stock.domain.response.AIResponse;
import com.apt.diva.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisService {

    private final AnalysisResultRepository analysisResultRepository;
    private final FinancialRepository financialRepository;
    private final MacroeconomicRepository macroeconomicRepository;
    private final NewsRepository newsRepository;
    private final InvestmentMovementRepository investmentmovementRepository;
    private final ExpertAnalysisRepository expertAnalysisRepository;
    private final SourceRepository sourceRepository;
    private final ReportRepository reportRepository;
    private final StockRepository stockRepository;

    private final RestTemplate restTemplate;

    @Value("${ai.server.url}")
    private String aiServerUrl;

    public FinancialResponse getFinancial(String stockCode) {

        String fullUrl = aiServerUrl + "/financial";

        AIRequest aiRequest = new AIRequest(stockCode);

        AIResponse aiResponse = restTemplate.postForObject(
                fullUrl,
                aiRequest,
                AIResponse.class
        );

        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {
            throw new RuntimeException("AI 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 analysis_result_id 추출
        Long financialId = aiResponse.getAnalysisResultId();

        Financial financial = financialRepository.findByFinancialId(financialId);

        return FinancialResponse.builder()
                .content(financial.getContent())
                .build();
    }


    public MacroeconomicsResponse getMacroeconomics(String stockCode) {

        String fullUrl = aiServerUrl + "/economy";

        AIRequest aiRequest = new AIRequest(stockCode);

        AIResponse aiResponse = restTemplate.postForObject(
                fullUrl,
                aiRequest,
                AIResponse.class
        );

        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {
            throw new RuntimeException("AI 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 analysis_result_id 추출
        Long macroeconomicId = aiResponse.getAnalysisResultId();
        Macroeconomics macroeconomic=macroeconomicRepository.findByMacroeconomicsId(macroeconomicId);

        return MacroeconomicsResponse.builder()
                .content(macroeconomic.getContent())
                .build();
    }

    public NewsResponse getNews(String stockCode){

        String fullUrl = aiServerUrl + "/news";

        AIRequest aiRequest = new AIRequest(stockCode);

        AIResponse aiResponse = restTemplate.postForObject(
                fullUrl,
                aiRequest,
                AIResponse.class
        );

        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {
            throw new RuntimeException("AI 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 analysis_result_id 추출
        Long newsId = aiResponse.getAnalysisResultId();

        News news = newsRepository.findByNewsId(newsId);
        return NewsResponse.builder()
                .content(news.getContent())
                .build();
    }

    public InvestmentMovementResponse getInvestmentMovement(String stockCode){

        Financial financial = financialRepository.findByFinancialId(Long.parseLong(stockCode));

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        InvestmentMovement investmentMovement = investmentmovementRepository.findByInvestmentMovementId(analysisResult.getInvestmentMovementId());

        return InvestmentMovementResponse.builder()
                .content(investmentMovement.getContent())
                .build();

    }

    public ExpertAnalysisResponse getExpertAnalysis(String stockCode){

        String fullUrl = aiServerUrl + "/expert";

        System.out.println(fullUrl);
        System.out.println(stockCode);

        AIRequest aiRequest = new AIRequest(stockCode);
        System.out.println(aiRequest.getStockCode());

        AIResponse aiResponse = restTemplate.postForObject(
                fullUrl,
                aiRequest,
                AIResponse.class
        );


        System.out.println("aiResponse:"+aiResponse.getAnalysisResultId());

        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {

            throw new RuntimeException("AI 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 analysis_result_id 추출
        Long expertAnalysisId = aiResponse.getAnalysisResultId();

        ExpertAnalysis expertAnalysis = expertAnalysisRepository.findByExpertAnalysisId(expertAnalysisId);

        return ExpertAnalysisResponse.builder()
                .content(expertAnalysis.getContent())
                .build();

    }

    public SourceResponse getSource(String stockCode){

        Financial financial = financialRepository.findByFinancialId(Long.parseLong(stockCode));

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        Source source=sourceRepository.findBySourceId(analysisResult.getSourceId());

        return SourceResponse.builder()
                .url(source.getUrl())
                .build();
    }

    public ReportResponse getReport(String stockCode){

        Financial financial = financialRepository.findByFinancialId(Long.parseLong(stockCode));

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        Report report=reportRepository.findByReportId(analysisResult.getReportId());

        return ReportResponse.builder()
                .reportUrl(report.getReportUrl())
                .build();
    }

}
