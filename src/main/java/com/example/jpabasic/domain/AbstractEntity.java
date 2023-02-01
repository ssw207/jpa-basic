package com.example.jpabasic.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractEntity<ID> implements Persistable<ID> {
    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew; // isNew 플래그로 save시 영속화 여부를 판단한다
    }

    @PrePersist // 영속화 되면 false로 변경
    @PostLoad
    void markNotNew() {
        this.isNew = false;
        System.out.println("isNew? = " + isNew);
    }
}
