package com.example.wells_forage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "securities")
public class Security {

    @Id
    @Column(name = "sec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sec_id;

    @Column(name = "sec_name")
    private String sec_name;

    @Column(name = "category")
    private String category;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

}
