package com.victorgarciarubio.idea_rating_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluation_sentences")
public class EvaluationSentence extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "type", columnDefinition = "ENUM('pro','con')", nullable = false)
    private String type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idea_id"
    )
    private Idea idea;

    @ManyToOne
    @JoinColumn(
            name = "evaluation_weight_id", nullable = false
    )
    private EvaluationWeight weight;


    @JsonIgnore
    @OneToMany(
            mappedBy = "evaluationSentence", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    List<UserIdeaEvaluation> userIdeaEvaluationList;

    @JsonIgnore
    @Column(name = "create_time")
    public LocalDateTime createTime;

    @JsonIgnore
    @Column(name = "update_time")
    public LocalDateTime updateTime;

    public float computeRating() {
        Float score;
        if (userIdeaEvaluationList != null){
            score = userIdeaEvaluationList.stream()
                    .map(UserIdeaEvaluation::computeRating)
                    .reduce(weight.getWeight(), Float::sum);
        } else {
            score = weight.getWeight();
        }
        return type.equals("CON") ? score * (-1) : score;
    }
}


