package de.tum.cit.aet.artemis.exercise.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import de.tum.cit.aet.artemis.exercise.domain.versioning.ReviewCommentComponentType;

/**
 * DTO for creating or updating a review comment
 */
public class ReviewCommentDTO {

    private Long id;

    @NotNull
    private ReviewCommentComponentType componentType;

    @Size(max = 500)
    private String path;

    private Integer line;

    @NotNull
    @Size(min = 1, message = "Comment body must not be empty")
    private String body;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewCommentComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ReviewCommentComponentType componentType) {
        this.componentType = componentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
