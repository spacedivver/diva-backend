package com.apt.diva.stock.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

//    @JsonProperty("analysis_result_id") // JSON 필드명과 매핑
    private Long analysisResultId;

}
