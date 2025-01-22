package com.apt.diva.report.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportResponse {
    private String reportUrl;
}
