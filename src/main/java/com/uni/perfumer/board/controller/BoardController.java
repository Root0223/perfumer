package com.uni.perfumer.board.controller;


import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.ApiResponse;
import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @PostMapping("/board")
  public ResponseEntity<List<BoardDTO>> boardPost(@RequestBody BoardDTO boardDTO){
      return new ResponseEntity<>(boardService.boardList(boardDTO), HttpStatus.OK);
  }

  @GetMapping("/board")
    public ResponseEntity<List<BoardDTO>> boardGet(){
      return new ResponseEntity<List<BoardDTO>>(boardService.boardList(new BoardDTO()), HttpStatus.OK);
  }

  @DeleteMapping("/board/{boardNo}")
    public ResponseEntity<?> boardDelete(@PathVariable Integer boardNo){
      ResponseEntity<ApiResponse> responseEntity;

      try {
          boardService.deleteBoard(boardNo);
          responseEntity = new ResponseEntity<>(new ApiResponse(true,
                  boardNo + "번째 글이 삭제 되었습니다"), HttpStatus.OK);
       } catch (Exception e){
          responseEntity = new ResponseEntity<>(new ApiResponse(false, e.getMessage()),HttpStatus.BAD_REQUEST);

      }
      return responseEntity;
      }
  }



