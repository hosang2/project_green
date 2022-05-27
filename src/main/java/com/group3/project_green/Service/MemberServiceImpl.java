package com.group3.project_green.Service;

import com.group3.project_green.DTO.MemberDTO;
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
}
