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
    @Test
    void sightsInsert(){
        Sights sights = Sights.builder()
                    .name("관광지테스트")
                    .category("관광지종류")
                    .build();
        sightRepository.save(sights);
    }
    @Test
    void foodInsert(){
        Food food = Food.builder()
                .name("음식테스트")
                .category("음식종류")
                .build();
        foodRepository.save(food);
    }
    @Test
    void accomInsert(){
        Accom accom = Accom.builder()
                .name("숙박테스트")
                .category("숙박종류")
                .build();
        accomRepository.save(accom);
    }
    @Test
    void postInsert() {
        for (int i = 0; i < 10; i++) {
            Sights sights = Sights.builder()
                    .sid(1L)
                    .build();
            Member member = Member.builder()
                    .id(1L)
                    .build();
            Accom accom = Accom.builder()
                    .aid(1L)
                    .build();
            Food food = Food.builder()
                    .fid(1L)
                    .build();
            Post post = Post.builder()
                    .content("food insert 테스트내용"+i)
                    .likeNum(5L)
                    .title("food insert 테스트타이틀"+i)
                    .member(member)
                    .sights(sights)
                    .build();
            postRepository.save(post);
        }
    }
}
