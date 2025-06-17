package Contribution.spring.application.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class GetBoardListResponse {
    private List<GetBoardResponse> boards;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public static GetBoardListResponse of(Page<GetBoardResponse> page) {
        return GetBoardListResponse.builder()
                .boards(page.getContent())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }
} 