package com.group3.project_green.heart;

import com.group3.project_green.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeartServiceImpl implements HeartService{

    private final HeartRepository repository;

    @Override
    public Long saveHeart(HeartDTO dto) {
        Heart heart = dtoToEntity(dto);
        repository.save(heart);
        return heart.getHno();
    }

    @Override
    public Long countHeart(Long pno) {
        Long result = repository.countHeartByPostPno(pno);
        System.out.println("pno: " + pno);
        System.out.println("service countHeart : "+ result);
        return result;
    }

    @Override
    public void remove(Long hno) {
        repository.deleteById(hno);
    }

    @Override
    public Heart getHeart(Long id,Long pno) {
        System.out.println("id: " +id);
        System.out.println("pno: " + pno);
        Heart heart = repository.findByMember_IdAndAndPostPno(id,pno);
        System.out.println("service heart : "+ heart );
        if(heart != null) return heart;
        else return null;
    }
}
