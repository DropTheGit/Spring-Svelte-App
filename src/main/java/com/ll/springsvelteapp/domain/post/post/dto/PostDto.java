package com.ll.springsvelteapp.domain.post.post.dto;

import com.ll.springsvelteapp.domain.post.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class PostDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private long authorId ;
    private String authorUsername ;
    private String title;
    private String body;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate= post.getModifyDate();
        this.authorId = post.getAuthor().getId();
        this.authorUsername = post.getAuthor().getUsername();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
}
