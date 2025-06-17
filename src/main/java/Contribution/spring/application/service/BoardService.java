package Contribution.spring.application.service;

import Contribution.spring.application.converter.BoardConverter;
import Contribution.spring.application.dto.CommandBoardRequest;
import Contribution.spring.application.dto.GetBoardListResponse;
import Contribution.spring.application.dto.GetBoardResponse;
import Contribution.spring.persistence.entity.Board;
import Contribution.spring.persistence.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    @Transactional
    public void createBoard(CommandBoardRequest request) {
        Board board = boardConverter.requestConverter(request);
        boardRepository.save(board);
    }

    public List<GetBoardResponse> getBoard() {
        List<Board> boards = boardRepository.findAll();
        List<GetBoardResponse> response = boardConverter.responseConverter(boards);
        return response;
    }

    public GetBoardListResponse getBoards(Pageable pageable) {
        Page<GetBoardResponse> boardPage = boardRepository.findAll(pageable)
                .map(boardConverter::responseConverter);
        return GetBoardListResponse.of(boardPage);
    }

    @Transactional
    public void updateBoard(CommandBoardRequest request, Long boardId) throws Exception {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("board is empty"));
        board.updateBoard(request.getBoardTitle(), request.getBoardAuthor());
    }

    @Transactional
    public void deleteBoard(Long boardId) throws Exception {
        if (!boardRepository.existsById(boardId)) {
            throw new Exception("board is not exist");
        }
        boardRepository.deleteById(boardId);
    }

}

