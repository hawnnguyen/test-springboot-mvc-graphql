package com.example.controller;

import com.example.model.Pattern;
import com.example.model.PatternInput;
import com.example.service.PatternService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PatternController {
    private final PatternService patternService;

    public PatternController(PatternService patternService) {
        this.patternService = patternService;
    }

    @QueryMapping
    public List<Pattern> patterns() {
        return patternService.getAllPatterns();
    }

    @QueryMapping
    public Pattern patternById(@Argument String id) {
        return patternService.getPatternById(id).orElse(null);
    }

    @QueryMapping
    public List<Pattern> patternsByCategory(@Argument String category) {
        return patternService.getPatternsByCategory(category);
    }

    @QueryMapping
    public List<Pattern> patternsByPhase(@Argument String phase) {
        return patternService.getPatternsByPhase(phase);
    }

    @MutationMapping
    public Pattern createPattern(@Argument PatternInput pattern) {
        return patternService.createPattern(pattern.toPattern());
    }
}
