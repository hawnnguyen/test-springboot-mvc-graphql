package com.example.service;

import com.example.entity.PatternEntity;
import com.example.model.Pattern;
import com.example.repository.PatternRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatternServiceImplTest {

    @Mock
    private PatternRepository patternRepository;

    private PatternServiceImpl patternService;

    private PatternEntity testPatternEntity;
    private Pattern testPattern;

    @BeforeEach
    void setUp() {
        patternService = new PatternServiceImpl(patternRepository);
        
        testPatternEntity = PatternEntity.builder()
                .id("1")
                .name("Test Pattern")
                .type("Test Type")
                .useCases("Test Use Cases")
                .description("Test Description")
                .category("Test Category")
                .phase("Test Phase")
                .build();

        testPattern = Pattern.builder()
                .id("1")
                .name("Test Pattern")
                .type("Test Type")
                .useCases("Test Use Cases")
                .description("Test Description")
                .category("Test Category")
                .phase("Test Phase")
                .build();
    }

    @Test
    void getAllPatterns_ShouldReturnListOfPatterns() {
        // Given
        when(patternRepository.findAll()).thenReturn(Arrays.asList(testPatternEntity));

        // When
        List<Pattern> result = patternService.getAllPatterns();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testPattern, result.get(0));
        verify(patternRepository).findAll();
    }

    @Test
    void getPatternById_WhenPatternExists_ShouldReturnPattern() {
        // Given
        when(patternRepository.findById("1")).thenReturn(Optional.of(testPatternEntity));

        // When
        Optional<Pattern> result = patternService.getPatternById("1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testPattern, result.get());
        verify(patternRepository).findById("1");
    }

    @Test
    void getPatternById_WhenPatternDoesNotExist_ShouldReturnEmpty() {
        // Given
        when(patternRepository.findById("nonexistent")).thenReturn(Optional.empty());

        // When
        Optional<Pattern> result = patternService.getPatternById("nonexistent");

        // Then
        assertFalse(result.isPresent());
        verify(patternRepository).findById("nonexistent");
    }

    @Test
    void getPatternsByCategory_ShouldReturnPatternsWithMatchingCategory() {
        // Given
        when(patternRepository.findAll()).thenReturn(Arrays.asList(testPatternEntity));

        // When
        List<Pattern> result = patternService.getPatternsByCategory("Test Category");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testPattern, result.get(0));
        verify(patternRepository).findAll();
    }

    @Test
    void getPatternsByPhase_ShouldReturnPatternsWithMatchingPhase() {
        // Given
        when(patternRepository.findAll()).thenReturn(Arrays.asList(testPatternEntity));

        // When
        List<Pattern> result = patternService.getPatternsByPhase("Test Phase");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testPattern, result.get(0));
        verify(patternRepository).findAll();
    }

    @Test
    void createPattern_ShouldSaveAndReturnNewPattern() {
        // Given
        when(patternRepository.save(any(PatternEntity.class))).thenReturn(testPatternEntity);

        // When
        Pattern result = patternService.createPattern(testPattern);

        // Then
        assertNotNull(result);
        assertEquals(testPattern, result);
        verify(patternRepository).save(any(PatternEntity.class));
    }

    @Test
    void updatePattern_WhenPatternExists_ShouldUpdateAndReturnPattern() {
        // Given
        when(patternRepository.findById("1")).thenReturn(Optional.of(testPatternEntity));
        when(patternRepository.save(any(PatternEntity.class))).thenReturn(testPatternEntity);

        Pattern updatedPattern = Pattern.builder()
                .id(testPattern.getId())
                .name("Updated Name")
                .type(testPattern.getType())
                .useCases(testPattern.getUseCases())
                .description(testPattern.getDescription())
                .category(testPattern.getCategory())
                .phase(testPattern.getPhase())
                .build();

        // When
        Pattern result = patternService.updatePattern("1", updatedPattern);

        // Then
        assertNotNull(result);
        verify(patternRepository).findById("1");
        verify(patternRepository).save(any(PatternEntity.class));
    }

    @Test
    void deletePattern_ShouldCallRepositoryDelete() {
        // When
        patternService.deletePattern("1");

        // Then
        verify(patternRepository).deleteById("1");
    }
}
