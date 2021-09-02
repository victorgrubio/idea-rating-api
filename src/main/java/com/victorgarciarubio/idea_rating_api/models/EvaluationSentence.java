package com.victorgarciarubio.idea_rating_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="evaluation_sentences")
public class EvaluationSentence extends AuditEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type" , columnDefinition="ENUM('PRO','CON')" ,nullable = false )
    private SentenceType type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="idea_id", nullable=false, insertable=false
    )
    private Idea idea;

    @ManyToOne
    @JoinColumn(
            name="evaluation_weight_id", nullable=false, insertable=false
    )
    private EvaluationWeight weight;

}


enum SentenceType {
    PRO,
    CON
}