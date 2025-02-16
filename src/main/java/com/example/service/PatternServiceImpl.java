package com.example.service;

import com.example.entity.PatternEntity;
import com.example.model.Pattern;
import com.example.repository.PatternRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatternServiceImpl implements PatternService {
    private final PatternRepository patternRepository;

    public PatternServiceImpl(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    private Pattern mapToPattern(PatternEntity entity) {
        return Pattern.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .useCases(entity.getUseCases())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .phase(entity.getPhase())
                .build();
    }

    @Override
    public List<Pattern> getAllPatterns() {
        return patternRepository.findAll().stream()
                .map(this::mapToPattern)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pattern> getPatternById(String id) {
        return patternRepository.findById(id)
                .map(this::mapToPattern);
    }

    @Override
    public List<Pattern> getPatternsByCategory(String category) {
        return patternRepository.findAll().stream()
                .map(this::mapToPattern)
                .filter(pattern -> pattern.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pattern> getPatternsByPhase(String phase) {
        return patternRepository.findAll().stream()
                .map(this::mapToPattern)
                .filter(pattern -> pattern.getPhase().equalsIgnoreCase(phase))
                .collect(Collectors.toList());
    }

    @Override
    public Pattern createPattern(Pattern pattern) {
        PatternEntity entity = PatternEntity.builder()
                .id(pattern.getId())
                .name(pattern.getName())
                .type(pattern.getType())
                .useCases(pattern.getUseCases())
                .description(pattern.getDescription())
                .category(pattern.getCategory())
                .phase(pattern.getPhase())
                .build();
        
        return mapToPattern(patternRepository.save(entity));
    }

    @Override
    public Pattern updatePattern(String id, Pattern pattern) {
        PatternEntity existingEntity = patternRepository.findById(id).orElseThrow();
        existingEntity.setName(pattern.getName());
        existingEntity.setType(pattern.getType());
        existingEntity.setUseCases(pattern.getUseCases());
        existingEntity.setDescription(pattern.getDescription());
        existingEntity.setCategory(pattern.getCategory());
        existingEntity.setPhase(pattern.getPhase());
        return mapToPattern(patternRepository.save(existingEntity));
    }

    @Override
    public void deletePattern(String id) {
        patternRepository.deleteById(id);
    }
}
