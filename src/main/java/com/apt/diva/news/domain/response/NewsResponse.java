package com.apt.diva.news.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsResponse {
    private String content;
}
