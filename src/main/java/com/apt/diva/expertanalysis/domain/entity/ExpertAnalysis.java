package com.apt.diva.expertanalysis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class ExpertAnalysis {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="expert_analysis_id")
    private Long expertAnalysisId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
