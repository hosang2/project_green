package com.group3.project_green.Session;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Getter
public class SessionUser extends User implements OAuth2User,Serializable {
    private Long id;
    private String password;
    private String email;
    private Map<String, Object> attr;

    public SessionUser(Member member,
                       Collection<? extends GrantedAuthority> authorities,
                       Map<String, Object> attr) {
        super(member.getEmail(), member.getPassword(), authorities);
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.attr = attr;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }

    @Override
    public String getName() {
        return email;
    }
}
