package com.uni.perfumer.member.model.service;

import com.uni.perfumer.jwt.TokenProvider;
import com.uni.perfumer.member.model.dao.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberMapper memberMapper;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

}
