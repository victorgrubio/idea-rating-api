package com.victorgarciarubio.idea_rating_api.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="evaluation_weights")
public class EvaluationWeight extends AuditEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float weight;

    @OneToMany(mappedBy = "weight", fetch = FetchType.LAZY)
    private List<EvaluationSentence> evaluationSentenceList;
}
