package com.apt.diva.report.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Long reportId;

    @Column(name="report_url")
    private String reportUrl;

    @Column(name="created_at")
    private Long createdAt;

    @Column(name="updated_at")
    private Long updatedAt;

}
