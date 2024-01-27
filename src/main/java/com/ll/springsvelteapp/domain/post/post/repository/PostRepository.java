package com.ll.springsvelteapp.domain.post.post.repository;

import com.ll.springsvelteapp.domain.member.member.entity.Member;
import com.ll.springsvelteapp.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Member author);
}
