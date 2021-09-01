package com.scheduler.api.common.model;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseDateEntity {

    @CreationTimestamp
    @Column(name = "created_dttm", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdDttm;

    @UpdateTimestamp
    @Column(name = "updated_dttm", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime updatedDttm;

}
