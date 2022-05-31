package com.group3.project_green.Session;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String password;
    private String email;

    public SessionUser(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
