package com.apt.diva.investmentmovement.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class InvestmentMovement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="investment_movement_id")
    private Long investmentMovementId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
