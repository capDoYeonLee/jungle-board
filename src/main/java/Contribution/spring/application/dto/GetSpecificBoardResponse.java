package Contribution.spring.application.dto;

import Contribution.spring.persistence.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetSpecificBoardResponse {
    private String boardTitle;
    private String boardAuthor;
    private String boardContent;
    private List<GetCommentResponse> comments;
}
