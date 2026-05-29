package com.gymflow.gym_management.repository;

import com.gymflow.gym_management.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // العمليات الأساسية متوفرة تلقائياً
}