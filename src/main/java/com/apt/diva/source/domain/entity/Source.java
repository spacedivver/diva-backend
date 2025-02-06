package com.apt.diva.source.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="source_id")
    private Long sourceId;

    @Column(name="analysis_result_id")
    private Long analysisResultId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
