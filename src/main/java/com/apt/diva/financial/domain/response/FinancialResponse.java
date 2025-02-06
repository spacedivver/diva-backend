package com.apt.diva.financial.domain.response;

import com.apt.diva.source.domain.response.SourceResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FinancialResponse {

    private String content;
    private List<SourceResponse> sources;

}
