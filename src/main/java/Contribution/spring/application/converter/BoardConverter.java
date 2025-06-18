package Contribution.spring.application.converter;

import Contribution.spring.application.dto.CommandBoardRequest;
import Contribution.spring.application.dto.GetBoardResponse;
import Contribution.spring.application.dto.GetCommentResponse;
import Contribution.spring.application.dto.GetSpecificBoardResponse;
import Contribution.spring.persistence.entity.Board;
import Contribution.spring.persistence.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardConverter {

    public Board requestConverter(CommandBoardRequest request){
        return Board.builder()
                .boardTitle(request.getBoardTitle())
                .boardAuthor(request.getBoardAuthor())
                .boardContent(request.getBoardContent())
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

    public GetSpecificBoardResponse getSpecificBoardResponse(Board board) {
        return GetSpecificBoardResponse.builder()
                .boardTitle(board.getBoardTitle())
                .boardAuthor(board.getBoardAuthor())
                .boardContent(board.getBoardContent())
                .comments(
                        board.getComments().stream()
                                .filter(comment -> comment.getParent() == null)
                                .map(this::getCommentResponse)
                                .collect(Collectors.toList())
                ).build();
    }

    public GetCommentResponse getCommentResponse(Comment comment) {
        return GetCommentResponse.builder()
                .commentId(comment.getCommentId())
                .commentAuthor(comment.getCommentAuthor())
                .commentContent(comment.getCommentContent())
                .replies(
                        comment.getReplies().stream()
                                .map(this::getCommentResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
} 