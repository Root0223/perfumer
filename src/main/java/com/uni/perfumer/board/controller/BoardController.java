package com.uni.perfumer.board.controller;


import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.ApiResponse;
import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.board.model.service.BoardService;
import com.uni.perfumer.common.ResponseDTO;
import com.uni.perfumer.common.paging.Pagenation;
import com.uni.perfumer.common.paging.ResponseDtoWithPaging;
import com.uni.perfumer.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//  @PostMapping("/boards")
//    public ResponseEntity<?> boardInsert(@RequestBody BoardDTO boardDTO){
//     ResponseEntity<ApiResponse> responseEntity;
////     try {
//         boardService.boardInsert(boardDTO);
//         responseEntity = new ResponseEntity<>(new ApiResponse(true, "저장성공"), HttpStatus.OK);
//
////     } catch (Exception e) {
////        responseEntity = new ResponseEntity<>(new ApiResponse(false,  e.getMessage()),HttpStatus.BAD_REQUEST);
////     }
//     return responseEntity;
//  }


    @PostMapping(value = "/boards")
    public ResponseEntity<ResponseDTO> boardInsert(@ModelAttribute BoardDTO boardDTO){
        log.info("[BoardController] PostMapping BoardDTO : " + boardDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 등록 성공", boardService.boardInsert(boardDTO)));
    }

    @PutMapping(value = "/boards")
    public ResponseEntity<ResponseDTO> boardModify(@ModelAttribute BoardDTO boardDTO){




        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 업데이트 성공",  boardService.boardModify(boardDTO)));

//        } catch (Exception e) {
//            responseEntity = new ResponseEntity<>(new ApiResponse(false,  e.getMessage()),HttpStatus.BAD_REQUEST);
//        }


    }
//  @PostMapping("/board")
//  public ResponseEntity<List<BoardDTO>> boardPost(@RequestBody BoardDTO boardDTO){
//      return new ResponseEntity<>(boardService.boardList(boardDTO), HttpStatus.OK);
//  }

  @GetMapping("/boards")
    public ResponseEntity<ResponseDTO> boardList(@RequestParam(name="offset", defaultValue="1") String offset){

      log.info("[ProductController] selectProductListWithPaging : " + offset);

      int totalCount = boardService.selectBoardTotal();
      int limit = 10;
      int buttonAmount = 5;
      SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);

      log.info("[ProductController] selectCriteria : " + selectCriteria);

      ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
      responseDtoWithPaging.setPageInfo(selectCriteria);
      responseDtoWithPaging.setData(boardService.boardList(selectCriteria));

      return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
  }

//    @GetMapping("/board/{boardCode}")
//    public ResponseEntity<List<BoardDTO>> boardDetail(@PathVariable Integer boardCode){
//        return new ResponseEntity<List<BoardDTO>>(boardService.boardDetail(new ApiResponse(true,boardCode + "번째 글이 삭제 되었습니다"), HttpStatus.OK);
//    }

    @GetMapping("/boards/{boardCode}")
    public ResponseEntity<?> boardDetail(@PathVariable Integer boardCode){
        ResponseEntity<ApiResponse> responseEntity;

        try {

            log.info(String.valueOf(boardCode));
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, boardCode + "번째 글이 조회 되었습니다", boardService.boardDetail(boardCode)));

        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),HttpStatus.BAD_REQUEST);

        }

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



