package com.victorgarciarubio.idea_rating_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="evaluation_sentences")
public class EvaluationSentence extends AuditEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String content;

    private SentenceType type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idea_id", nullable=false)
    private Idea idea;

    @OneToOne
    private EvaluationWeight weight;

}


enum SentenceType {
    PRO,
    CON
}