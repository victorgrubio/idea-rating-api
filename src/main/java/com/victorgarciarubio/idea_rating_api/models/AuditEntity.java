package com.victorgarciarubio.idea_rating_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AuditEntity implements DatabaseModel {

    @CreationTimestamp
    @JsonIgnore
    @Column(name="create_time", updatable = false)
    public LocalDateTime createTime;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name="update_time")
    public LocalDateTime updateTime;

}
