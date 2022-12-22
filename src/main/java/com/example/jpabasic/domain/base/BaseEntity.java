package com.example.jpabasic.domain.base;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime created;
    private LocalDateTime modified;
}
