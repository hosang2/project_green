package com.group3.project_green.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class Accom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    private String name;

    private String category;



}
