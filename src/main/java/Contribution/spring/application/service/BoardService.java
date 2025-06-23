package Contribution.spring.application.service;

import Contribution.spring.application.converter.BoardConverter;
import Contribution.spring.application.converter.CommentConverter;
import Contribution.spring.application.dto.*;
import Contribution.spring.persistence.entity.Board;
import Contribution.spring.persistence.entity.Comment;
import Contribution.spring.persistence.repository.BoardRepository;
import Contribution.spring.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardConverter boardConverter;
    private final CommentConverter commentConverter;

    @Transactional
    public Long createBoard(CommandBoardRequest request) {
        Board board = boardConverter.requestConverter(request);
        Long saveId = boardRepository.save(board).getId();
        return saveId;
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
    public void updateBoard(CommandBoardRequest request, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("no exist board"));
        board.updateBoard(request.getBoardTitle(), request.getBoardContent());
    }

    @Transactional
    public void deleteBoard(Long boardId) throws Exception {
        if (!boardRepository.existsById(boardId)) {
            throw new Exception("board is not exist");
        }
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void createComment(CommandCommentRequest request, Long boardId) {
        Board board = findBoard(boardId);

        Comment parent = null;

        if (request.getParentId() != null) {
            parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("no parent comment"));
        }

        Comment comment = Comment.builder()
                .board(board)
                .commentAuthor(request.getCommentAuthor())
                .commentContent(request.getCommentContent())
                .parent(parent)
                .build();
        commentRepository.save(comment); // 인서트 쿼리 하나만 나가는데 업데이트 쿼리가 같이 나가서
    }

    public List<GetCommentResponse> getComments() {
        List<GetCommentResponse> comments = commentRepository.findAll().stream()
                .map(commentConverter::responseConverter)
                .collect(Collectors.toList());
        return comments;
    }

    public Board findBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("no exist board"));
    }

    public GetSpecificBoardResponse getSpecificBoardResponse(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("no exist board"));
        return boardConverter.getSpecificBoardResponse(board);
    }

    @Transactional
    public void updateComment(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("no exist comment"));
        comment.updateComment(request.getCommentContent());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("no exist comment");
        }
        commentRepository.deleteById(commentId);
    }
}

