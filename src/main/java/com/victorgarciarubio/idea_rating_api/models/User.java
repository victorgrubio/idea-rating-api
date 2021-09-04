package com.victorgarciarubio.idea_rating_api.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victorgarciarubio.idea_rating_api.dtos.requests.DtoRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User extends AuditEntity {

    @Id
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserIdeaEvaluation> userIdeaEvaluationList;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Idea> ideaList;

}
