package com.uni.perfumer.board.model.dao;

import com.uni.perfumer.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

     void boardInsert(BoardDTO boardDTO);
    void boardModify(BoardDTO boardDTO);

    List<BoardDTO> boardList(BoardDTO boardDTO);

    List<BoardDTO> boardDetail(BoardDTO boardCode);


    void boardDelete(Integer boardNo);

}
