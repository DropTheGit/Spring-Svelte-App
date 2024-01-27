package com.ll.springsvelteapp.domain.member.member.controller;

import com.ll.springsvelteapp.domain.member.member.dto.MemberDto;
import com.ll.springsvelteapp.domain.member.member.service.MemberService;
import com.ll.springsvelteapp.global.rq.Rq;
import com.ll.springsvelteapp.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MembersController {
    private final MemberService memberService;
    private final Rq rq;

    @AllArgsConstructor
    @Getter
    public static class LoginResponseBody {
        private MemberDto item;
    }

    @Getter
    @Setter
    public static class LoginRequestBody {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public RsData<LoginResponseBody> login(
            @Valid @RequestBody LoginRequestBody body
    ) {
     RsData<MemberService.AuthAndMakeTokensResponseBody> authAndMakeTokensRS =
             this.memberService.authAndMakeTokens(body.username, body.password);

     rq.setCrossDomainCookie("refreshToken", authAndMakeTokensRS.getData().getRefreshToken());
     rq.setCrossDomainCookie("accessToken", authAndMakeTokensRS.getData().getAccessToken());

     return RsData.of(
             authAndMakeTokensRS.getResultCode(),
             authAndMakeTokensRS.getMsg(),
             new LoginResponseBody(
                     new MemberDto(
                             authAndMakeTokensRS.getData().getMember()
                     )
             )
     );
    }
}