package com.apt.diva.newspolicy.repository;

import com.apt.diva.newspolicy.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPolicyRepository extends JpaRepository<News, Long> {
    News findByNewsPolicyId(Long newsPolicyId);
}
