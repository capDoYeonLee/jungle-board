package Contribution.spring.controller;

import Contribution.spring.application.dto.*;
import Contribution.spring.application.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private static final Integer PAGE_DEFAULT_NUMBER = 10;

    @PostMapping("/board")
    public void createBoard(@RequestBody CommandBoardRequest request) {
        boardService.createBoard(request);
    }

    @GetMapping("/board")
    public GetBoardListResponse getBoardList(
            @PageableDefault(size = 10) Pageable pageable) {
        return boardService.getBoards(pageable);
    }

    @GetMapping("/board/{boardId}")
    public GetSpecificBoardResponse getBoard(@PathVariable("boardId") Long boardId) {
        return boardService.getSpecificBoardResponse(boardId);
    }

    @PutMapping("/board/{boardId}")
    public void updateBoard(@RequestBody CommandBoardRequest request, @PathVariable("boardId") Long boardId) throws Exception {
        boardService.updateBoard(request, boardId);
    }

    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
    }

    @PostMapping("/board/{boardId}/comment")
    public void createComment(@RequestBody CommandCommentRequest request, @PathVariable("boardId") Long boardId) {
        boardService.createComment(request, boardId);
    }

    @GetMapping("/board/{boardId}/comment/{commentId}")
    public List<GetCommentResponse> getComments(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
        return boardService.getComments();
    }

    @PutMapping("/board/{boardId}/comment/{commentId}")
    public void updateComment(@PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest request) {
        boardService.updateComment(commentId, request);
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        boardService.deleteComment(commentId);
    }

}
