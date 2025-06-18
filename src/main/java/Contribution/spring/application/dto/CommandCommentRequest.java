package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommandCommentRequest {
    private String commentAuthor;
    private String commentContent;
    private Long parentId;
}
