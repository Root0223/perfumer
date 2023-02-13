package com.uni.perfumer.board.model.service;

import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
public class BoardService {

    @Autowired
    private final BoardMapper boardMapper;


    public BoardService(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public String boardInsert(BoardDTO boardDTO){
        int result =0;
        result = boardMapper.boardInsert(boardDTO);
        return (result > 0) ? "게시판 등록 성공" : "게시판 등록 실패";
    }

    @Transactional
    public String boardModify(BoardDTO boardDTO){
        boardMapper.boardModify(boardDTO);
        int result = 0;

        result = boardMapper.boardModify(boardDTO);

        return (result > 0 ) ? "수정 성공 " : "수정실패";

    }

    public int selectBoardTotal() {
        log.info("[BoardService] selectBoardTotal Start ===================================");
        int result = boardMapper.selectBoardTotal();

        log.info("[BoardService] selectBoardTotal End ===================================");
        return result;
    }


    public Object boardList(SelectCriteria selectCriteria){
        log.info("[ProductService] selectProductListWithPaging Start ===================================");
        List<BoardDTO> boardList = boardMapper.boardList(selectCriteria);


        log.info("[ProductService] selectProductListWithPaging End ===================================");
        return boardList;
    }

    public BoardDTO boardDetail(Integer boardCode){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardCode(boardCode);
        BoardDTO result = boardMapper.boardDetail(boardDTO);
        return result;
    }

    public  void deleteBoard(Integer boardCode){
        boardMapper.boardDelete(boardCode);
    }




}
