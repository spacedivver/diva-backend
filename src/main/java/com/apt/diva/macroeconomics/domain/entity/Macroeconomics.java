package com.apt.diva.macroeconomics.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Macroeconomics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="macroeconomics_id")
    private Long macroeconomicsId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
