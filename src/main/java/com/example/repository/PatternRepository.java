package com.example.repository;

import com.example.entity.PatternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternRepository extends JpaRepository<PatternEntity, String> {
}
