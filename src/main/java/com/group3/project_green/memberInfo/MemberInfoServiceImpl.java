package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService{

    private final MemberRepository memberRepository;

    @Override
    public MemberInfoDTO get(Long id) {
        MemberInfoDTO memberInfoDTO = entityToDTO(memberRepository.getById(id));
        return memberInfoDTO;
    }

    @Override
    public void quit(Long id){
        memberRepository.deleteById(id);
    }
}
