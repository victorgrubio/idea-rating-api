package com.victorgarciarubio.idea_rating_api.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User extends AuditEntity {

    @Id
    private String username;
}
