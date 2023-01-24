package com.uni.perfumer.board.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {

    private int boardNo;
    private String boardTitle;
    private String boardContents;
    private String boardAuthor;

    private Date boardRegdate;
    private int boardReads;

}