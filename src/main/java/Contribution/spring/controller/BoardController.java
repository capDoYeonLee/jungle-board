package Contribution.spring.controller;

import Contribution.spring.application.dto.CommandBoardRequest;
import Contribution.spring.application.dto.GetBoardListResponse;
import Contribution.spring.application.dto.UpdateBoardRequest;
import Contribution.spring.application.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/board/{boardId}")
    public void updateBoard(@RequestBody CommandBoardRequest request, @PathVariable Long boardId) throws Exception {
        boardService.updateBoard(request, boardId);
    }

    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
    }

}
