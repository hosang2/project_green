package com.group3.project_green.Service;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.entity.Member;
import com.group3.project_green.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{
    private final MemberRepository repository;

    @Override
    public MemberDTO get(Long id) {
        MemberDTO result = entityToDTO(repository.getById(id));
        return result;
    }

    @Override
    public int saveMember(Member member) {
        System.out.println("서비스 save : " +member);
        repository.save(member);
        return 0;
    }
    @Override
    public Member login(Member member) {
        System.out.println("로그인 서비스 : " +member);
        Member isLogin = repository.login(member.getEmail(), member.getPassword());
        return isLogin;
    }

}
