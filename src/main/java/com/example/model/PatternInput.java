package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatternInput {
    private String id;
    private String name;
    private String type;
    private String useCases;
    private String description;
    private String category;
    private String phase;

    public Pattern toPattern() {
        return Pattern.builder()
                .id(id)
                .name(name)
                .type(type)
                .useCases(useCases)
                .description(description)
                .category(category)
                .phase(phase)
                .build();
    }
}
