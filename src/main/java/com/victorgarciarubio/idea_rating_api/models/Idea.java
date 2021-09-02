package com.victorgarciarubio.idea_rating_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="idea")
public class Idea extends AuditEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "idea", fetch = FetchType.LAZY)
    private List<EvaluationSentence> evaluationSentenceList;

    public void setId(Long ideaId) {
        id = ideaId;
    }
}
