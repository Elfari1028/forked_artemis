package de.tum.cit.aet.artemis.exercise.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseVersion;

/**
 * A DTO representing an exercise version.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExerciseVersionDTO {

    private Long id;

    private Long exerciseId;

    private String problemStatement;

    private Instant createdDate;

    // Default constructor for JSON deserialization
    public ExerciseVersionDTO() {
    }

    public ExerciseVersionDTO(Long id, Long exerciseId, String problemStatement, Instant createdDate) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.problemStatement = problemStatement;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    // Conversion methods

    /**
     * Converts an exercise version to an exercise version DTO.
     *
     * @param exerciseVersion the exercise version to convert
     * @return the exercise version DTO
     */
    public static ExerciseVersionDTO of(ExerciseVersion exerciseVersion) {
        if (exerciseVersion == null) {
            return null;
        }
        return new ExerciseVersionDTO(exerciseVersion.getId(), exerciseVersion.getExercise() != null ? exerciseVersion.getExercise().getId() : null,
                exerciseVersion.getProblemStatement(), exerciseVersion.getCreatedDate());
    }
}
