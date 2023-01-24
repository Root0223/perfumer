package com.uni.perfumer.board.model.dao;

import com.uni.perfumer.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public void boardInsert(BoardDTO boardDTO);

    List<BoardDTO> boardList(BoardDTO boardDTO);

    void boardDelete(Integer boardNo);

}
