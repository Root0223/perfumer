package com.uni.perfumer.member.model.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String grantType;
    private String memberName;
    private String status;
    private String accessToken;


    private Long accessTokenExpiresIn;


    public TokenDTO(String grantType, String memberName, String accessToken, String status, Long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.memberName = memberName;
        this.status = status;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
