package com.apt.diva.news.domain.response;

import com.apt.diva.source.domain.response.SourceResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsResponse {
    private String content;
    private List<SourceResponse> sources;
}
