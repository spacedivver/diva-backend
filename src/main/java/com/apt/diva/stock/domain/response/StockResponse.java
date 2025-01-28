package com.apt.diva.stock.domain.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {

    private String companyName;
    private String stockCode;

}
