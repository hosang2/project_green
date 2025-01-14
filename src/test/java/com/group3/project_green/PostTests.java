package com.group3.project_green;

import com.group3.project_green.entity.*;
import com.group3.project_green.repository.AccomRepository;
import com.group3.project_green.repository.FoodRepository;
import com.group3.project_green.repository.PostRepository;
import com.group3.project_green.repository.SightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PostTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private AccomRepository accomRepository;
    @Autowired
    private SightRepository sightRepository;


    @Test
    void insertDummy(){

//        for(int i=0; i<6; i++){
//
//            Sights sights = Sights.builder()
//                    .name("관광지"+i)
//                    .category("관광지종류"+i)
//                    .build();
//
//            sightRepository.save(sights);
//
//        }

//        for(int i=0; i<3; i++){
//
//            Accom accom =Accom.builder()
//                    .name("숙소"+i)
//                    .category("숙소"+i)
//                    .build();
//
//            accomRepository.save(accom);
//
//        }

        for(int i=1; i<=6; ++i){

            Optional<Sights> temp = sightRepository.findById(new Long(i));

            Post post1 = Post.builder()
                    .sights(temp.get())
                    .title("제목"+i)
                    .likeNum(new Long(i))
                    .content("내용"+i)
                    .member(Member.builder().id(i%4L+1).build())
                    .build();

            postRepository.save(post1);

        }
    }


}
