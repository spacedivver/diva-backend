package com.apt.diva.newspolicy.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class NewsPolicy {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="news_policy_id")
    private Long newsPolicyId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
