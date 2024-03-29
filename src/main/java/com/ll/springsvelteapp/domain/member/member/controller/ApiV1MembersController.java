package com.ll.springsvelteapp.domain.member.member.controller;

import com.ll.springsvelteapp.domain.member.member.dto.MemberDto;
import com.ll.springsvelteapp.domain.member.member.entity.Member;
import com.ll.springsvelteapp.domain.member.member.service.MemberService;
import com.ll.springsvelteapp.global.rq.Rq;
import com.ll.springsvelteapp.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/members", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "ApiV1ArticlesController", description = "게시물 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
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
    @Operation(summary = "로그인, 로그인 성공시 accessToken, refreshToken 쿠키 설정")
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

    @Getter
    public static class MeResponseBody {
        private MemberDto item;

        public MeResponseBody(Member member) {
            this.item = new MemberDto(member);
        }
    }

    @GetMapping(value = "/me", consumes = ALL_VALUE)
    @Operation(summary = "내 정보")
    public RsData<MeResponseBody> getMe() {
        return RsData.of(
                "200",
                "내 정보 가져오기 성공",
                new MeResponseBody(rq.getMember())
        );
    }
}