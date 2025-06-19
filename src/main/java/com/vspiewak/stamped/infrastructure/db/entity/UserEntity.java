package com.vspiewak.stamped.infrastructure.db.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false, unique = true)
  private UUID id;

  @Column(name = "username")
  private String username;
}
