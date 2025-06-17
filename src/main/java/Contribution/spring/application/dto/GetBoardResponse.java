package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBoardResponse {
    private Long id;
    private String boardTitle;
    private String boardAuthor;
}
