package com.apt.diva.source.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceResponse {
    private String url;
}
