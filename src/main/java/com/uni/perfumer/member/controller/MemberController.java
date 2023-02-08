package com.uni.perfumer.member.controller;

import com.uni.perfumer.common.ResponseDTO;
import com.uni.perfumer.member.model.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



    @RestController
    @RequestMapping("/api/v1")
    public class MemberController {
        private final MemberService memberService;

        public MemberController(MemberService memberService) {
            this.memberService = memberService;
        }

        @GetMapping("/members/{memberId}")
        public ResponseEntity<ResponseDTO> selectMyMemberInfo(@PathVariable String memberId) {

            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", memberService.selectMyInfo(memberId)));
        }



    }


