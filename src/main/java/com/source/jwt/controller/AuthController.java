package com.source.jwt.controller;

import com.source.jwt.model.dto.MemberRequestDto;
import com.source.jwt.model.dto.MemberResponseDto;
import com.source.jwt.model.dto.TokenDto;
import com.source.jwt.model.dto.TokenRequestDto;
import com.source.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

//**
@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
		return ResponseEntity.ok(authService.signup(memberRequestDto));
	}

	@PostMapping("/singin")
	public TokenDto login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse httpServletResponse) {
		TokenDto tokenDto = authService.login(memberRequestDto); // 원래 리턴은 ResponseEntity<TokenDto>였고, 얘가 리턴 값에 들어갔음 밑에서 헤더로 다 빼준거임 ㅇㅇ 맴버 자체를 리턴하거나 스트링 리턴도 나쁘지 않을듯 ㅇㅇㅇ
		httpServletResponse.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
		httpServletResponse.setHeader("Refresh-Token", tokenDto.getRefreshToken());
		httpServletResponse.setHeader("Access-Token-Expire-Time", String.valueOf(tokenDto.getAccessTokenExpiresIn()));
//		return "환영합니다. "+memberRequestDto.getUsername() + "님";
		return tokenDto;
	}

	@PostMapping("/reissue")
	public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
		return ResponseEntity.ok(authService.reissue(tokenRequestDto));
	}
}
