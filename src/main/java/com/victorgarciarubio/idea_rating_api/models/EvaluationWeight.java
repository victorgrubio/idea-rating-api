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
@Table(name="evaluation_weights")
public class EvaluationWeight extends AuditEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float weight;

    @JsonIgnore
    @Column(name="create_time")
    public LocalDateTime createTime;

    @JsonIgnore
    @Column(name="update_time")
    public LocalDateTime updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "weight", fetch = FetchType.LAZY)
    private List<EvaluationSentence> evaluationSentenceList;
}
