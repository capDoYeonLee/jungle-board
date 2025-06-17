package Contribution.spring.application.converter;

import Contribution.spring.application.dto.CommandBoardRequest;
import Contribution.spring.application.dto.GetBoardResponse;
import Contribution.spring.persistence.entity.Board;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardConverter {

    public Board requestConverter(CommandBoardRequest request){
        return Board.builder()
                .boardTitle(request.getBoardTitle())
                .boardAuthor(request.getBoardAuthor())
                .build();
    }

    public GetBoardResponse responseConverter(Board board) {
        return GetBoardResponse.builder()
                .id(board.getId())
                .boardTitle(board.getBoardTitle())
                .boardAuthor(board.getBoardAuthor())
                .build();
    }

    public List<GetBoardResponse> responseConverter(List<Board> boards) {
        return boards.stream()
                .map(this::responseConverter)
                .collect(Collectors.toList());
    }
} 