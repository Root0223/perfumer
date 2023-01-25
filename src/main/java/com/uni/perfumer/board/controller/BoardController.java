package com.uni.perfumer.board.controller;


import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.ApiResponse;
import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.board.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {



    private final BoardService boardService;
    @Autowired
    private BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    private  BoardMapper boardMapper;

  @PostMapping("/boards")
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

    @PutMapping("/boards")
    public ResponseEntity<?> boardModify(@RequestBody BoardDTO boardDTO){
        ResponseEntity<ApiResponse> responseEntity;
        try {
            boardService.boardModify(boardDTO);



            responseEntity = new ResponseEntity<>(new ApiResponse(true, "변경완료"), HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(new ApiResponse(false,  e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }
//  @PostMapping("/board")
//  public ResponseEntity<List<BoardDTO>> boardPost(@RequestBody BoardDTO boardDTO){
//      return new ResponseEntity<>(boardService.boardList(boardDTO), HttpStatus.OK);
//  }

  @GetMapping("/boards")
    public ResponseEntity<List<BoardDTO>> boardList(){
      return new ResponseEntity<List<BoardDTO>>(boardService.boardList(new BoardDTO()), HttpStatus.OK);
  }

//    @GetMapping("/board/{boardCode}")
//    public ResponseEntity<List<BoardDTO>> boardDetail(@PathVariable Integer boardCode){
//        return new ResponseEntity<List<BoardDTO>>(boardService.boardDetail(new ApiResponse(true,boardCode + "번째 글이 삭제 되었습니다"), HttpStatus.OK);
//    }

    @GetMapping("/boards/{boardCode}")
    public ResponseEntity<?> boardDetail(@PathVariable Integer boardCode){
        ResponseEntity<ApiResponse> responseEntity;

        try {
            boardService.boardDetail(boardCode);
            log.info(String.valueOf(boardCode));
            responseEntity = new ResponseEntity<>(new ApiResponse(true,
                    boardCode + "번째 글이 조회 되었습니다"), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(new ApiResponse(false, e.getMessage()),HttpStatus.BAD_REQUEST);

        }
        return responseEntity;
    }

    @DeleteMapping("/boards/{boardCode}")
    public ResponseEntity<?> boardDelete(@PathVariable Integer boardCode){
      ResponseEntity<ApiResponse> responseEntity;

      try {
          boardService.deleteBoard(boardCode);
          responseEntity = new ResponseEntity<>(new ApiResponse(true,
                  boardCode + "번째 글이 삭제 되었습니다"), HttpStatus.OK);
       } catch (Exception e){
          responseEntity = new ResponseEntity<>(new ApiResponse(false, e.getMessage()),HttpStatus.BAD_REQUEST);

      }
      return responseEntity;
      }

/*    @Transactional
    @PostMapping(value = "/board/manage/regist", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    @PostMapping(value = "/board/manage/regist", consumes = {"multipart/form-data"})
//    @PostMapping(value = "/board/manage/regist")
    public ResponseEntity<ResponseDTO> registPost(@RequestPart String boardTitle, @RequestPart String memberId, @RequestPart MultipartFile boardImg) {
//    public ResponseEntity<ResponseDTO> registPost(@ModelAttribute BoardDTO boardDTO) {

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle(boardTitle);
        boardDTO.setMemberId(memberId);
        boardDTO.setBoardImage(boardImg);

        log.info(boardDTO.toString());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "게시글 등록됨", boardService.registPost(boardDTO)));
    }*/
  }



