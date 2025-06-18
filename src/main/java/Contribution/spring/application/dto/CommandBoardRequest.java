package Contribution.spring.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandBoardRequest {
    private String boardTitle;
    private String boardAuthor;
    private String boardContent;
}
