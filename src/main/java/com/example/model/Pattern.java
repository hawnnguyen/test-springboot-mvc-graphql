package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pattern {
    private String id;
    private String name;
    private String type;
    private String useCases;
    private String description;
    private String category;
    private String phase;
}
