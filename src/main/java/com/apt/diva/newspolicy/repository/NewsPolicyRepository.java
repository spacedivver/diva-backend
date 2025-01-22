package com.apt.diva.newspolicy.repository;

import com.apt.diva.newspolicy.domain.entity.NewsPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPolicyRepository extends JpaRepository<NewsPolicy, Long> {
    NewsPolicy findByNewsPolicyId(Long newsPolicyId);
}
