package com.group3.project_green;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberRole;
import com.group3.project_green.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertDummy(){

        Member member1 = Member.builder()
                .email("test1@naver.com")
                .password("1111")
                .build();

        member1.addMemberRole(MemberRole.USER);

        memberRepository.save(member1);

    }

}
