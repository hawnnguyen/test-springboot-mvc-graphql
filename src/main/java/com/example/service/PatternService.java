package com.example.service;

import com.example.model.Pattern;

import java.util.List;
import java.util.Optional;

public interface PatternService {
    List<Pattern> getAllPatterns();
    Optional<Pattern> getPatternById(String id);
    List<Pattern> getPatternsByCategory(String category);
    List<Pattern> getPatternsByPhase(String phase);
    Pattern createPattern(Pattern pattern);
    Pattern updatePattern(String id, Pattern pattern);
    void deletePattern(String id);
}
