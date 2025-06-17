package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommandBoardRequest {
    private String boardTitle;
    private String boardAuthor;
}
