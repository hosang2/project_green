package com.group3.project_green.heart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group3.project_green.BaseEntity;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Heart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hno;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
