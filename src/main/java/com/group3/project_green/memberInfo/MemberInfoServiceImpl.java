package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.entity.Post;
import com.group3.project_green.memberInfo.repository.InfoCommentRepository;
import com.group3.project_green.memberInfo.repository.InfoMemberRepository;
import com.group3.project_green.memberInfo.repository.InfoPostRepository;
import com.group3.project_green.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService{

    private final InfoMemberRepository infoMemberRepository;

    private final InfoPostRepository infoPostRepository;

    private final InfoCommentRepository infoCommentRepository;

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

        // 마지막으로 회원정보를 삭제합니다.
        infoMemberRepository.deleteById(id);

    }
}
