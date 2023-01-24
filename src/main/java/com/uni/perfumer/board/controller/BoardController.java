package com.uni.perfumer.board.controller;


import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.ApiResponse;
import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1")
public class BoardController {



    private final BoardService boardService;
    @Autowired
    private BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    private  BoardMapper boardMapper;

  @PutMapping("/board")
    public ResponseEntity<?> boardInsert(@RequestBody BoardDTO boardDTO){
     ResponseEntity<ApiResponse> responseEntity;
     try {
         boardService.boardInsert(boardDTO);
         responseEntity = new ResponseEntity<>(new ApiResponse(true, "저장성공"), HttpStatus.OK);

     } catch (Exception e) {
        responseEntity = new ResponseEntity<>(new ApiResponse(false,  e.getMessage()),HttpStatus.BAD_REQUEST);
     }
     return responseEntity;
  }
}
