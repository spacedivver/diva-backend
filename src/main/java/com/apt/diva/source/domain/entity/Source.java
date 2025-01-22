package com.apt.diva.source.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="source_id")
    private Long sourceId;

    @Column(name="url")
    private String url;

}
