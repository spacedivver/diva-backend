package com.apt.diva.analysis.service;

import com.apt.diva.analysis.domain.entity.AnalysisResult;
import com.apt.diva.analysis.repository.AnalysisResultRepository;
import com.apt.diva.expertanalysis.domain.entity.ExpertAnalysis;
import com.apt.diva.expertanalysis.domain.response.ExpertAnalysisResponse;
import com.apt.diva.expertanalysis.repository.ExpertAnalysisRepository;
import com.apt.diva.financial.domain.entity.Financial;
import com.apt.diva.financial.domain.response.FinancialResponse;
import com.apt.diva.financial.repository.FinancialRepository;
import com.apt.diva.investmentmovement.repository.InvestmentMovementRepository;
import com.apt.diva.macroeconomics.repository.MacroeconomicRepository;
import com.apt.diva.news.domain.entity.News;
import com.apt.diva.news.domain.response.NewsResponse;
import com.apt.diva.news.repository.NewsRepository;
import com.apt.diva.report.domain.entity.Report;
import com.apt.diva.report.domain.response.ReportResponse;
import com.apt.diva.report.repository.ReportRepository;
import com.apt.diva.source.domain.entity.Category;
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

import java.util.List;
import java.util.stream.Collectors;


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
        Long analysisResultId = aiResponse.getAnalysisResultId();

        AnalysisResult analysisResult = analysisResultRepository.findByAnalysisResultId(analysisResultId);

        Financial financial = financialRepository.findByFinancialId(analysisResult.getFinancialId());

        // 여러 건의 Source 정보를 불러옴
        List<Source> sources = sourceRepository.findByAnalysisResultIdAndCategory(analysisResultId, Category.FINANCIAL);
        if (sources == null || sources.isEmpty()) {
            throw new RuntimeException("FINANCIAL에 해당하는 Source가 없습니다.");
        }

        // 각 Source를 SourceResponse로 변환
        List<SourceResponse> sourceResponses = sources.stream()
                .map(source -> SourceResponse.builder()
                        .title(source.getTitle())
                        .description(source.getDescription())
                        .url(source.getUrl())
                        .build())
                .collect(Collectors.toList());

        return FinancialResponse.builder()
                .content(financial.getContent())
                .sources(sourceResponses)
                .build();
    }


//    public MacroeconomicsResponse getMacroeconomics(String stockCode) {
//
//        String fullUrl = aiServerUrl + "/economy";
//
//        AIRequest aiRequest = new AIRequest(stockCode);
//
//        AIResponse aiResponse = restTemplate.postForObject(
//                fullUrl,
//                aiRequest,
//                AIResponse.class
//        );
//
//        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {
//            throw new RuntimeException("AI 서버 응답이 null입니다");
//        }
//
//        // AI 서버로부터 받은 analysis_result_id 추출
//        Long macroeconomicId = aiResponse.getAnalysisResultId();
//        Macroeconomics macroeconomic=macroeconomicRepository.findByMacroeconomicsId(macroeconomicId);
//
//        return MacroeconomicsResponse.builder()
//                .content(macroeconomic.getContent())
//                .build();
//    }

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
        Long analysisResultId = aiResponse.getAnalysisResultId();

        AnalysisResult analysisResult = analysisResultRepository.findByAnalysisResultId(analysisResultId);

        News news = newsRepository.findByNewsId(analysisResult.getNewsId());

        // 여러 건의 Source 정보를 불러옴
        List<Source> sources = sourceRepository.findByAnalysisResultIdAndCategory(analysisResultId, Category.NEWS);
        if (sources == null || sources.isEmpty()) {
            throw new RuntimeException("NEWS에 해당하는 Source가 없습니다.");
        }

        // 각 Source를 SourceResponse로 변환
        List<SourceResponse> sourceResponses = sources.stream()
                .map(source -> SourceResponse.builder()
                        .title(source.getTitle())
                        .description(source.getDescription())
                        .url(source.getUrl())
                        .build())
                .collect(Collectors.toList());

        return NewsResponse.builder()
                .content(news.getContent())
                .sources(sourceResponses)
                .build();
    }

//    public InvestmentMovementResponse getInvestmentMovement(String stockCode){
//
//        Financial financial = financialRepository.findByFinancialId(Long.parseLong(stockCode));
//
//        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());
//
//        InvestmentMovement investmentMovement = investmentmovementRepository.findByInvestmentMovementId(analysisResult.getInvestmentMovementId());
//
//        return InvestmentMovementResponse.builder()
//                .content(investmentMovement.getContent())
//                .build();
//
//    }

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

        System.out.println("aiResponse:" + aiResponse.getAnalysisResultId());

        if (aiResponse == null || aiResponse.getAnalysisResultId() == null) {
            throw new RuntimeException("AI 서버 응답이 null입니다");
        }

        // AI 서버로부터 받은 analysis_result_id 추출
        Long analysisResultId = aiResponse.getAnalysisResultId();

        AnalysisResult analysisResult = analysisResultRepository.findByAnalysisResultId(analysisResultId);

        ExpertAnalysis expertAnalysis = expertAnalysisRepository.findByExpertAnalysisId(analysisResult.getExpertAnalysisId());

        // 여러 건의 Source 정보를 불러옴
        List<Source> sources = sourceRepository.findByAnalysisResultIdAndCategory(analysisResultId, Category.EXPERT_ANALYSIS);
        if (sources == null || sources.isEmpty()) {
            throw new RuntimeException("EXPERT_ANALYSIS에 해당하는 Source가 없습니다.");
        }

        // 각 Source를 SourceResponse로 변환
        List<SourceResponse> sourceResponses = sources.stream()
                .map(source -> SourceResponse.builder()
                        .title(source.getTitle())
                        .description(source.getDescription())
                        .url(source.getUrl())
                        .build())
                .collect(Collectors.toList());

        return ExpertAnalysisResponse.builder()
                .content(expertAnalysis.getContent())
                .sources(sourceResponses)
                .build();

    }

//    public SourceResponse getSource(String stockCode){
//
//        Financial financial = financialRepository.findByFinancialId(Long.parseLong(stockCode));
//
//        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());
//
//        Source source=sourceRepository.findBySourceId(analysisResult.getSourceId());
//
//        return SourceResponse.builder()
//                .url(source.getUrl())
//                .build();
//    }

    public ReportResponse getReport(String stockCode){

        String fullUrl = aiServerUrl + "/report";

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
        Long analysisResultId = aiResponse.getAnalysisResultId();

        AnalysisResult analysisResult=analysisResultRepository.findByAnalysisResultId(analysisResultId);

        Report report=reportRepository.findByReportId(analysisResult.getReportId());

        return ReportResponse.builder()
                .content(report.getContent())
                .build();
    }

}
