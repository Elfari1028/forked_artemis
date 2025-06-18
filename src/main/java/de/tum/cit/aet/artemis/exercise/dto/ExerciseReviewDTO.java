package de.tum.cit.aet.artemis.exercise.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for submitting an exercise review with one or more comments
 */
public class ExerciseReviewDTO {

    @NotNull
    private Long exerciseVersionId;

    @Valid
    @Size(min = 1, message = "At least one comment is required")
    private List<ReviewCommentDTO> comments = new ArrayList<>();

    // Getters and setters
    public Long getExerciseVersionId() {
        return exerciseVersionId;
    }

    public void setExerciseVersionId(Long exerciseVersionId) {
        this.exerciseVersionId = exerciseVersionId;
    }

    public List<ReviewCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<ReviewCommentDTO> comments) {
        this.comments = comments;
    }

    public void addComment(ReviewCommentDTO comment) {
        this.comments.add(comment);
    }
}
