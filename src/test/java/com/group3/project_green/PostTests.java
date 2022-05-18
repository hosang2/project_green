package com.group3.project_green;

import com.group3.project_green.entity.Food;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.FoodRepository;
import com.group3.project_green.repository.PostRepository;
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

    @Test
    void insertDummy(){

//        for(int i=0; i<20; i++){
//
//            Food food1 = Food.builder()
//                    .name("음식" + i)
//                    .category("음식종류" + i)
//                    .build();
//
//            foodRepository.save(food1);
//
//        }


        for(int i=2; i<=21; ++i){

            Optional<Food> temp = foodRepository.findById(new Long(i));

            Post post1 = Post.builder()
                    .food(temp.get())
                    .title("제목"+i)
                    .likeNum(new Long(i))
                    .content("내용"+i)
                    .member(Member.builder().id(i%4L+1).build())
                    .build();

            postRepository.save(post1);

        }
    }


}
