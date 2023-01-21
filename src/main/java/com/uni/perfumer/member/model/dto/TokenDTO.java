package com.uni.perfumer.member.model.dto;

import lombok.Data;

@Data
public class TokenDTO {

        private String grantType;
        private String memberName;
        private String accessToken;

        private String status;
        private Long accessTokenExpiresIn;


    public TokenDTO(String grantType, String memberName, String accessToken, String status, Long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.memberName = memberName;
        this.accessToken = accessToken;
        this.status = status;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
