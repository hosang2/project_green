package com.group3.project_green.Service;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository repository;

    @Override
    public Long register(CommentDTO commentDTO) {
        Comment comment = dtoToEntity(commentDTO);
        repository.save(comment);
        return comment.getCno();
    }

    @Override
    public List<CommentDTO> getList(Long pno) {
        List<Comment> result = repository.getCommentsByPostOrderByCno(Post.builder().pno(pno).build());
        return result.stream().map(comment->entityToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public void modify(CommentDTO commentDTO) {
        Comment comment = dtoToEntity(commentDTO);
        repository.save(comment);
    }

    @Override
    public void remove(Long cno) {
        repository.deleteById(cno);
    }
}
