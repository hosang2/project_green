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
    public MemberDTO get(Long id) {
        MemberDTO result = entityToDTO(memberRepository.getById(id));
        return result;
    }

    @Override
    public void quit(Long id){
        memberRepository.deleteById(id);
    }
}
