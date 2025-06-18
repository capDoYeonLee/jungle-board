package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetCommentResponse {
    private Long commentId;
    private String commentContent;
    private String commentAuthor;
    private List<GetCommentResponse> replies;
}
