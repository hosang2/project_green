package com.group3.project_green.entity;

import com.group3.project_green.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true)
    private String email;

    @ManyToOne
    private MemberInfo memberInfo;


    // 유저 권한 (Member, Admin)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    // 회원가입 위한 생성자
    public Member(String email, String password, MemberRole memberRole){
        this.email = email;
        this.password = password;
        this.addMemberRole(memberRole);
    }

}
