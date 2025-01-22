package com.apt.diva.newspolicy.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsPolicyResponse {
    private String content;
}
