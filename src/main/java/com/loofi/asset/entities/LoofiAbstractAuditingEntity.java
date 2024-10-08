package com.loofi.asset.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class LoofiAbstractAuditingEntity extends LoofiAbstractEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @JoinColumn(name = "created_by")
  private String createdBy;

  @JoinColumn(name = "last_modified_by")
  private String lastModifiedBy;

}
