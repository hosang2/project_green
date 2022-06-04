package com.group3.project_green.entity;

import com.group3.project_green.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    @ManyToOne
    private Member member;

    private String name;

    private String phone;

    private String birthDate;

    private String address;

    private String detailAddress;

    private String location;

    private String favoriteFood;
    private String favoriteAccom;
    private String favoriteLocation;

}
