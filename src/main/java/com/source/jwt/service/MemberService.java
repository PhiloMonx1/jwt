package com.source.jwt.service;

import com.source.jwt.model.Member;
import com.source.jwt.model.dto.MemberResponseDto;
import com.source.jwt.repository.MemberRepository;
import com.source.jwt.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//**
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public Long getSigninUserId(){
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		return Long.valueOf(userId);
	}
	public Member getSinginUser(){
		return memberRepository.findById(getSigninUserId())
				.orElseThrow(()-> new RuntimeException("유저를 찾지 못했습니다."));
	}

	@Transactional(readOnly = true)
	public MemberResponseDto getMemberInfo(String email) {
		return memberRepository.findByUsername(email)
				.map(MemberResponseDto::of)
				.orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
	}

	@Transactional(readOnly = true)
	public MemberResponseDto getMyInfo() {
		return memberRepository.findById(SecurityUtil.getCurrentMemberId())
				.map(MemberResponseDto::of)
				.orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
	}
}
