package com.group3.project_green.security;

import com.group3.project_green.Session.SessionUser;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberRole;
import com.group3.project_green.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String password = passwordEncoder.encode("1111");

        Member user = saveOrUpdate(email, password);

        SessionUser sessionUser = new SessionUser(user,
                user.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toList()),
                oAuth2User.getAttributes());

        httpSession.setAttribute("user", sessionUser);

        return sessionUser;
    }

    public Member saveOrUpdate(String email, String password){

        Optional<Member> user = memberRepository.findByEmail(email);

        if(user.isPresent()){

            return user.get();

        } else {

            Member member = Member.builder()
                    .email(email)
                    .password(password)
                    .build();
            member.addMemberRole(MemberRole.USER);

            return memberRepository.save(member);
        }

    }
}
