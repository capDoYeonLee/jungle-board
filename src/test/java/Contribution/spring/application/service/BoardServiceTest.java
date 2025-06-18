package Contribution.spring.application.service;

import Contribution.spring.application.dto.CommandBoardRequest;
import Contribution.spring.persistence.entity.Board;
import Contribution.spring.persistence.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    void createBoardTest() {

        // given
        CommandBoardRequest request = CommandBoardRequest.builder()
                .boardTitle("테스트 제목")
                .boardAuthor("테스터")
                .boardContent("테스트 내용")
                .build();

        // when
        Long boardId = boardService.createBoard(request);

        // then
        Board board = boardRepository.findById(boardId).orElse(null);
        assertThat(board).isNotNull();
        assertThat(board.getBoardTitle()).isEqualTo("테스트 제목");
        assertThat(((Board) board).getBoardAuthor()).isEqualTo("테스터");
        assertThat(board.getBoardContent()).isEqualTo("테스트 내용");
    }

    @Test
    void getBoard() {
    }

    @Test
    void getBoards() {
    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void createComment() {
    }

    @Test
    void getComments() {
    }

    @Test
    void getSpecificBoardResponse() {
    }
}