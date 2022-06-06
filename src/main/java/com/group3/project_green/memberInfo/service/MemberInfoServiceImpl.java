package com.group3.project_green.memberInfo.service;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberInfo;
import com.group3.project_green.entity.Post;
import com.group3.project_green.memberInfo.MemberDetailDTO;
import com.group3.project_green.memberInfo.MemberInfoDTO;
import com.group3.project_green.memberInfo.repository.InfoCommentRepository;
import com.group3.project_green.memberInfo.repository.InfoMemberDetailRepository;
import com.group3.project_green.memberInfo.repository.InfoMemberRepository;
import com.group3.project_green.memberInfo.repository.InfoPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService{

    private final InfoMemberRepository infoMemberRepository;

    private final InfoPostRepository infoPostRepository;

    private final InfoCommentRepository infoCommentRepository;

    private final InfoMemberDetailRepository infoMemberDetailRepository;

    @Override
    public MemberInfoDTO get(Long id) {
        MemberInfoDTO memberInfoDTO = entityToDTO(infoMemberRepository.getById(id));
        return memberInfoDTO;
    }

    @Override
    public void quit(Long id){
        // 해당 회원이 작성한 모든 댓글을 삭제한다.
        infoCommentRepository.deleteByMember_Id(id);

        List<Post> posts = infoPostRepository.findByMemberId(id);

        for(Post post : posts){
            infoCommentRepository.deleteByPost_Pno(post.getPno());
        }

        // 해당 회원이 작성한 모든 게시글을 삭제한다.
        infoPostRepository.deleteByMember_Id(id);

        // 해당 회원의 회원 정보를 삭제합니다.
        infoMemberDetailRepository.deleteByMember_Id(id);

        // 마지막으로 회원정보를 삭제합니다.
        infoMemberRepository.deleteById(id);

    }

    @Override
    public void modifyInfo(Long id, MemberDetailDTO memberDetailDTO) {

        Optional<MemberInfo> origin = infoMemberDetailRepository.findByMemberId(id);

        if(origin.isPresent()){
            log.info("==========여기는 기존에 MemberINfo가 존재하는 경우==========");
            MemberInfo memberInfo = origin.get();

            // null값이라면 수정하지 않습니다.
            memberInfo.setName(memberDetailDTO.getName()!=null || !memberDetailDTO.getName().equals("") ?
                    memberDetailDTO.getName() : memberInfo.getName());
            memberInfo.setPhone(memberDetailDTO.getPhone()!=null || !memberDetailDTO.getPhone().equals("") ?
                    memberDetailDTO.getPhone() : memberInfo.getPhone());
            memberInfo.setBirthDate(memberDetailDTO.getBirthDate()!=null ?
                    memberDetailDTO.getBirthDate() : memberInfo.getBirthDate());
            memberInfo.setAddress(memberDetailDTO.getAddress()!=null || !memberDetailDTO.getAddress().equals("") ?
                    memberDetailDTO.getAddress() : memberInfo.getAddress());
            memberInfo.setDetailAddress(memberDetailDTO.getDetailAddress()!=null || !memberDetailDTO.getDetailAddress().equals("") ?
                    memberDetailDTO.getDetailAddress() : memberInfo.getDetailAddress());
            memberInfo.setLocation(memberDetailDTO.getLocation()!=null || !memberDetailDTO.getLocation().equals("") ?
                    memberDetailDTO.getLocation() : memberInfo.getLocation());
            memberInfo.setFavoriteFood(memberDetailDTO.getFavoriteFood()!=null || !memberDetailDTO.getFavoriteFood().equals("") ?
                    memberDetailDTO.getFavoriteFood() : memberInfo.getFavoriteFood());
            memberInfo.setFavoriteAccom(memberDetailDTO.getFavoriteAccom()!=null || !memberDetailDTO.getFavoriteAccom().equals("") ?
                    memberDetailDTO.getFavoriteAccom() : memberInfo.getFavoriteAccom());
            memberInfo.setFavoriteLocation(memberDetailDTO.getFavoriteLocation()!=null || !memberDetailDTO.getFavoriteLocation().equals("") ?
                    memberDetailDTO.getFavoriteLocation() : memberInfo.getFavoriteLocation());

            infoMemberDetailRepository.save(memberInfo);
        } else {
            log.info("==========여기는 기존에 MemberINfo가 존재하지 않는 경우==========");
            MemberInfo memberInfo = MemberInfo.builder()
                    .member(Member.builder().id(id).build())
                    .name(memberDetailDTO.getName())
                    .phone(memberDetailDTO.getPhone())
                    .birthDate(memberDetailDTO.getBirthDate())
                    .address(memberDetailDTO.getAddress())
                    .detailAddress(memberDetailDTO.getDetailAddress())
                    .location(memberDetailDTO.getLocation())
                    .favoriteFood(memberDetailDTO.getFavoriteFood())
                    .favoriteAccom(memberDetailDTO.getFavoriteAccom())
                    .favoriteLocation(memberDetailDTO.getFavoriteLocation())
                    .build();

            infoMemberDetailRepository.save(memberInfo);

        }

    }

    @Override
    public MemberDetailDTO getDetail(Long id) {


        Optional<MemberInfo> memberInfoOptional = infoMemberDetailRepository.findByMemberId(id);

        if(memberInfoOptional.isPresent()) {
            MemberInfo memberInfo = memberInfoOptional.get();
            MemberDetailDTO memberDetailDTO = MemberDetailDTO.builder()
                    .ino(memberInfo.getIno())
                    .name(memberInfo.getName())
                    .address(memberInfo.getAddress())
                    .detailAddress(memberInfo.getDetailAddress())
                    .birthDate(memberInfo.getBirthDate())
                    .phone(memberInfo.getPhone())
                    .location(memberInfo.getLocation())
                    .favoriteFood(memberInfo.getFavoriteFood())
                    .favoriteAccom(memberInfo.getFavoriteAccom())
                    .favoriteLocation(memberInfo.getFavoriteLocation())
                    .build();
            return memberDetailDTO;
        }

        return MemberDetailDTO.builder().build();
    }

}
