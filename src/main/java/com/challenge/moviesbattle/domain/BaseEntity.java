package com.challenge.moviesbattle.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Getter
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Getter
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
