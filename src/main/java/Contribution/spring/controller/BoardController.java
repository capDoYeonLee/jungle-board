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
    public GetSpecificBoardResponse getBoard(@PathVariable Long boardId) {
        return boardService.getSpecificBoardResponse(boardId);
    }

    @PutMapping("/board/{boardId}")
    public void updateBoard(@RequestBody CommandBoardRequest request, @PathVariable Long boardId) throws Exception {
        boardService.updateBoard(request, boardId);
    }

    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
    }

    @PostMapping("/board/{boardId}/comment")
    public void createComment(@RequestBody CommandCommentRequest request, @PathVariable Long boardId) {
        boardService.createComment(request, boardId);
    }

    @GetMapping("/board/{boardId}/comment/{commentId}")
    public List<GetCommentResponse> getComments(@PathVariable Long boardId, @PathVariable Long commentId) {
        return boardService.getComments();
    }

}
