package com.uni.perfumer.board.model.dao;

import com.uni.perfumer.board.model.dto.BoardDTO;
import com.uni.perfumer.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

//     void boardInsert(BoardDTO boardDTO);

    int boardInsert(BoardDTO boardDTO);
    int boardModify(BoardDTO boardDTO);

    List<BoardDTO> boardList(SelectCriteria selectCriteria);

    BoardDTO boardDetail(BoardDTO boardCode);

    int selectBoardTotal();

    void boardDelete(Integer boardNo);

}
