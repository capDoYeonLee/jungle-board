package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateBoardRequest {
    private Long id;
    private String boardTitle;
    private String boardAuthor;
}
