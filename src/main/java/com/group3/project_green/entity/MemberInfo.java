package com.group3.project_green.entity;

import com.group3.project_green.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfo extends BaseEntity {

    @Id
    private Long ino;

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
