package com.uni.perfumer.board.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {

    private Integer boardCode;
    private String boardTitle;
    private String boardContents;
    private String boardAuthor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date boardRegdate;
//    private int boardReads;

}
