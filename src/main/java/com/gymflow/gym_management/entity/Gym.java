package com.gymflow.gym_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Gyms")
@Data
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gymId;

    private String name;
    private String address;
    private String phone;
    
    private String subscriptionStatus = "active";

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}