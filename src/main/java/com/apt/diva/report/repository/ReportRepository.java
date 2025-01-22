package com.apt.diva.report.repository;

import com.apt.diva.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByReportId(Long reportId);
}
