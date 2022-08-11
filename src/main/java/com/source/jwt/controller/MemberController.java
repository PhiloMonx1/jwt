package com.source.jwt.controller;

import com.source.jwt.model.dto.MemberResponseDto;
import com.source.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//**
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/me")
	public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
		return ResponseEntity.ok(memberService.getMyInfo());
	}

	@GetMapping("/{username}")
	public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String username) {
		return ResponseEntity.ok(memberService.getMemberInfo(username));
	}
}
