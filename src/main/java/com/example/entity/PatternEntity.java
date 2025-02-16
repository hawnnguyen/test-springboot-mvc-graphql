package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patterns")
public class PatternEntity {
    @Id
    private String id;
    private String name;
    private String type;
    @Column(name = "use_cases")
    private String useCases;
    private String description;
    private String category;
    private String phase;
}
