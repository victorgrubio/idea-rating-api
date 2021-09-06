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
            mappedBy = "idea", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    List<EvaluationSentence> evaluationSentenceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id", nullable = false
    )
    private User user;

    public float computeRating() {
        float rating = 0.0F;
        for (EvaluationSentence evaluationSentence : evaluationSentenceList) {
            rating += evaluationSentence.computeRating();
        }
        return rating;
    }
}
