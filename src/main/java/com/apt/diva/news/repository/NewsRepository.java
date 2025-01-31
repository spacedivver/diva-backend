package com.apt.diva.news.repository;

import com.apt.diva.news.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    News findByNewsId(Long newsId);
}
