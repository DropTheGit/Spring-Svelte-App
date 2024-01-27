package com.ll.springsvelteapp.domain.member.member.dto;

import com.ll.springsvelteapp.domain.member.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
public class MemberDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String username;
    private List<String> authorities;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.createDate = member.getCreateDate();
        this.modifyDate = member.getModifyDate();
        this.username = member.getUsername();
        this.authorities = member.getAuthoritiesAsStringList();
    }
}
