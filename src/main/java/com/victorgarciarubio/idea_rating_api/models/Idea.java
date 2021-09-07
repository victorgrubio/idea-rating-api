package com.victorgarciarubio.idea_rating_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ideas")
public class Idea extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(
            mappedBy = "idea", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true
    )
    List<EvaluationSentence> evaluationSentenceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id", nullable = false
    )
    private User user;

    public float computeRating() {
        return evaluationSentenceList.stream()
             .map(EvaluationSentence::computeRating).reduce(0F, Float::sum);
    }
}
