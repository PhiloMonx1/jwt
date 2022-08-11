package com.source.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//**
@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private Authority authority;

	@Builder
	public Member(String username, String password, Authority authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
}
