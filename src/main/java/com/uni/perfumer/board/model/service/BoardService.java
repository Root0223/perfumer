package com.uni.perfumer.board.model.service;

import com.uni.perfumer.board.model.dao.BoardMapper;
import com.uni.perfumer.board.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {

    @Autowired
    private final BoardMapper boardMapper;


    public BoardService(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    public void boardInsert(BoardDTO boardDTO){
        boardMapper.boardInsert(boardDTO);
    }

    public void boardModify(BoardDTO boardDTO){
        boardMapper.boardModify(boardDTO);
    }

    public List<BoardDTO> boardList(BoardDTO boardDTO){
        List<BoardDTO> result = boardMapper.boardList(boardDTO);
        return  result;
    }

    public List<BoardDTO> boardDetail(Integer boardCode){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardCode(boardCode);
        List<BoardDTO> result = boardMapper.boardDetail(boardDTO);
        return result;
    }

    public  void deleteBoard(Integer boardCode){
        boardMapper.boardDelete(boardCode);
    }




}
