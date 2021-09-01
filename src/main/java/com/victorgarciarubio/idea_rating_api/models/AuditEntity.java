package com.victorgarciarubio.idea_rating_api.models;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AuditEntity implements DatabaseModel {

    @Column(name="create_time")
    public LocalDateTime createTime;

    @Column(name="update_time")
    public LocalDateTime updateTime;

}
