package com.apt.diva.financial.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Financial {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="financial_id")
    private Long financialId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
