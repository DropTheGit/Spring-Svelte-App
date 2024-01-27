package com.ll.springsvelteapp.domain.post.post.service;

import com.ll.springsvelteapp.domain.member.member.entity.Member;
import com.ll.springsvelteapp.domain.post.post.entity.Post;
import com.ll.springsvelteapp.domain.post.post.repository.PostRepository;
import com.ll.springsvelteapp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    public List<Post> findByAuthor(Member member) {
       return this.postRepository.findByAuthor(member);
    }

    @Transactional
    public RsData<Post> write(Member member, String title, String body) {
        Post post = Post.builder()
                .author(member)
                .title(title)
                .body(body)
                .build();

        postRepository.save(post);

        return RsData.of("200-1", "글 작성 성공", post);
    }
}
