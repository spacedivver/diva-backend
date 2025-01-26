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
import com.apt.diva.newspolicy.domain.entity.NewsPolicy;
import com.apt.diva.newspolicy.domain.response.NewsPolicyResponse;
import com.apt.diva.newspolicy.repository.NewsPolicyRepository;
import com.apt.diva.report.domain.entity.Report;
import com.apt.diva.report.domain.response.ReportResponse;
import com.apt.diva.report.repository.ReportRepository;
import com.apt.diva.source.domain.entity.Source;
import com.apt.diva.source.domain.response.SourceResponse;
import com.apt.diva.source.repository.SourceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisService {

    private final AnalysisResultRepository analysisResultRepository;
    private final FinancialRepository financialRepository;
    private final MacroeconomicRepository macroeconomicRepository;
    private final NewsPolicyRepository newsPolicyRepository;
    private final InvestmentMovementRepository investmentmovementRepository;
    private final ExpertAnalysisRepository expertAnalysisRepository;
    private final SourceRepository sourceRepository;
    private final ReportRepository reportRepository;

    public FinancialResponse getFinancial(String stockCode) {

            Financial financial = financialRepository.findByStockCode(stockCode);

            return FinancialResponse.builder()
                    .content(financial.getContent())
                    .build();
    }


    public MacroeconomicsResponse getMacroeconomics(String stockCode) {

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        Macroeconomics macroeconomic=macroeconomicRepository.findByMacroeconomicsId(analysisResult.getMacroeconomicsId());

        return MacroeconomicsResponse.builder()
                .content(macroeconomic.getContent())
                .build();
    }

    public NewsPolicyResponse getNews(String stockCode){

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        NewsPolicy newsPolicy = newsPolicyRepository.findByNewsPolicyId(analysisResult.getNewsPolicyId());
        return NewsPolicyResponse.builder()
                .content(newsPolicy.getContent())
                .build();
    }

    public InvestmentMovementResponse getInvestmentMovement(String stockCode){

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        InvestmentMovement investmentMovement = investmentmovementRepository.findByInvestmentMovementId(analysisResult.getInvestmentMovementId());

        return InvestmentMovementResponse.builder()
                .content(investmentMovement.getContent())
                .build();

    }

    public ExpertAnalysisResponse getExpertAnalysis(String stockCode){

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        ExpertAnalysis expertAnalysis = expertAnalysisRepository.findByExpertAnalysisId(analysisResult.getExpertAnalysisId());

        return ExpertAnalysisResponse.builder()
                .content(expertAnalysis.getContent())
                .build();

    }

    public SourceResponse getSource(String stockCode){

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        Source source=sourceRepository.findBySourceId(analysisResult.getSourceId());

        return SourceResponse.builder()
                .url(source.getUrl())
                .build();
    }

    public ReportResponse getReport(String stockCode){

        Financial financial = financialRepository.findByStockCode(stockCode);

        AnalysisResult analysisResult=analysisResultRepository.findByFinancialId(financial.getFinancialId());

        Report report=reportRepository.findByReportId(analysisResult.getReportId());

        return ReportResponse.builder()
                .reportUrl(report.getReportUrl())
                .build();
    }

}
