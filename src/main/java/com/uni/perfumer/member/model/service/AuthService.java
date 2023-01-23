package com.uni.perfumer.member.model.service;

import com.uni.perfumer.exception.DuplicatedUsernameException;
import com.uni.perfumer.exception.LoginFailedException;
import com.uni.perfumer.jwt.TokenProvider;
import com.uni.perfumer.member.model.dao.MemberMapper;
import com.uni.perfumer.member.model.dto.MemberDTO;
import com.uni.perfumer.member.model.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberMapper memberMapper;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDTO signup(MemberDTO memberDTO){
        log.info("[AuthService] Signup Start ===================================");
        log.info("[AuthService] MemberRequestDto {}", memberDTO);

        if (memberMapper.selectByEmail(memberDTO.getMemberEmail()) != null){
            log.info("[AuthService] 이메일이 중복됩니다");
            throw new DuplicatedUsernameException("이메일이 중복됩니다");

        }
        log.info("[AuthService] Member Signup Start ==============================");
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        int result = memberMapper.insertMember(memberDTO);
        log.info("[AuthService] Member Insert Result {}", result > 0 ? "회원 가입 성공" : "회원 가입 실패");
        log.info("[AuthService] Signup End ==============================");

        return memberDTO;
    }

    @Transactional
    public TokenDTO login(MemberDTO memberDTO) {
        log.info("[AuthService] login Start =========");
        log.info("[AuthService] {}", memberDTO);

        MemberDTO member = memberMapper.findByMemberId(memberDTO.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호입니다"));


        //저장된 비밀번호랑 입력한 비밀번호랑 비교
        if (!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())) {
            throw new LoginFailedException("잘못된 아이디 또는 비밀번호 입니다");
        }
            TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);

            return tokenDTO;
        }


    }
