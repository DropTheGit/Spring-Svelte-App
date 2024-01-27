package com.ll.springsvelteapp.domain.post.post.controller;

import com.ll.springsvelteapp.domain.member.member.entity.Member;
import com.ll.springsvelteapp.domain.post.post.dto.PostDto;
import com.ll.springsvelteapp.domain.post.post.service.PostService;
import com.ll.springsvelteapp.domain.post.post.entity.Post;
import com.ll.springsvelteapp.global.rq.Rq;
import com.ll.springsvelteapp.global.rsData.RsData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1PostsController {

    private final Rq rq;
    private final PostService postService;

    @Getter
    private static class GetMineResponseBody {
        private final List<PostDto> items;

        public GetMineResponseBody(List<Post> items){
            this.items = items.stream()
                    .map(x -> new PostDto(x))
                    .toList();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mine")
    public RsData<GetMineResponseBody> getMine() {
        Member member = rq.getMember();

        List<Post> posts = postService.findByAuthor(member);

        return RsData.of(
                "200",
                "글 가져오기 성공",
                new GetMineResponseBody(posts)
        );
    }


}
