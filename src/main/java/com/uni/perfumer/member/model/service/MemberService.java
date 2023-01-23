package com.uni.perfumer.member.model.service;

import com.uni.perfumer.exception.UserNotFoundException;
import com.uni.perfumer.member.model.dao.MemberMapper;
import com.uni.perfumer.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        log.info("[CustomUserDetailsService] ===================================");
        log.info("[CustomUserDetailsService] loadUserByUsername {}", memberId);

        return memberMapper.findByMemberId(memberId)
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(memberId + "> 찾을 수 없습니다."));
    }

    private MemberDTO addAuthorities(MemberDTO memberDTO) {
        memberDTO.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberDTO.getMemberRole())));
        memberDTO.setStatus("새싹");
        return memberDTO;
    }

    @GetMapping
    public MemberDTO selectMyInfo(@PathVariable String memberId) {
        log.info("[MemberService] getMyInfo Start ==============================");

        MemberDTO memberDTO = memberMapper.selectByMemberId(memberId);
        log.info("[MemberService] {}", memberDTO);
        log.info("[MemberService] getMyInfo End ==============================");

        return memberDTO;
    }
}
