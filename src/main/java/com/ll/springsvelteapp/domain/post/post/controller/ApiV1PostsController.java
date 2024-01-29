package com.ll.springsvelteapp.domain.post.post.controller;

import com.ll.springsvelteapp.domain.member.member.entity.Member;
import com.ll.springsvelteapp.domain.post.post.dto.PostDto;
import com.ll.springsvelteapp.domain.post.post.entity.Post;
import com.ll.springsvelteapp.domain.post.post.service.PostService;
import com.ll.springsvelteapp.global.rq.Rq;
import com.ll.springsvelteapp.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Tag(name = "ApiV1ArticlesController", description = "게시물 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
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

    @GetMapping(value = "/mine", consumes = ALL_VALUE)
    @Operation(summary = "내 글 리스트")
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
