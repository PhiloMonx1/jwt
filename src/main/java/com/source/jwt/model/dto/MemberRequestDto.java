package com.source.jwt.model.dto;

import com.source.jwt.model.Authority;
import com.source.jwt.model.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;


//**
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

	private String username;
	private String password;

	public Member toMember(PasswordEncoder passwordEncoder) {
		return Member.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.authority(Authority.ROLE_USER)
				.build();
	}

	public UsernamePasswordAuthenticationToken toAuthentication() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
}
