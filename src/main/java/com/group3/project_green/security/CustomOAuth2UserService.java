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
        String password = passwordEncoder.encode("1111");   // 비밀번호는 1111로 FIX합니다.

        // 기존에 있는 유저인지 확인합니다.
        Member user = saveOrUpdate(email, password);

        System.out.println("==================user : " + user.toString());

        SessionUser sessionUser = new SessionUser(user,
                user.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toList()),
                oAuth2User.getAttributes());

        httpSession.setAttribute("user", sessionUser);

        return sessionUser;
    }

    public Member saveOrUpdate(String email, String password){

        // 로그인한 이메일로 DB를 탐색합니다.
        Optional<Member> user = memberRepository.findByEmail(email);

        // 이미 있는 회원이라면 그 정보를 리턴합니다.
        if(user.isPresent()){
            System.out.println("==============in saveOrUpdate : " + user.get().toString());
            return user.get();

        } else {    // 없는 회원이라면, 1111의 비밀번호로 신규 가입시킵니다.

            Member member = Member.builder()
                    .email(email)
                    .password(password)
                    .build();
            member.addMemberRole(MemberRole.USER);

            return memberRepository.save(member);
        }

    }
}
