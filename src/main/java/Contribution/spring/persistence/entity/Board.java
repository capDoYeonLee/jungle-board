package Contribution.spring.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String boardTitle;

    private String boardAuthor;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void updateBoard(String boardTitle, String boardAuthor) {
        this.boardTitle = boardTitle;
        this.boardAuthor = boardAuthor;
    }
}
