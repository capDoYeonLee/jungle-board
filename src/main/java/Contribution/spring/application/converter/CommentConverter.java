package Contribution.spring.application.converter;

import Contribution.spring.application.dto.GetBoardResponse;
import Contribution.spring.application.dto.GetCommentResponse;
import Contribution.spring.persistence.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverter {

    public GetCommentResponse responseConverter(Comment comment) {
        return GetCommentResponse.builder()
                .commentAuthor(comment.getCommentAuthor())
                .commentContent(comment.getCommentContent())
                .build();
    }

    public List<GetCommentResponse> responseConverter(List<Comment> comments) {
        return comments.stream()
                .map(this::responseConverter)
                .collect(Collectors.toList());
    }
}
