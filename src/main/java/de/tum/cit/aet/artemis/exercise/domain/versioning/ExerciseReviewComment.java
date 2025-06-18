package de.tum.cit.aet.artemis.exercise.domain.versioning;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import de.tum.cit.aet.artemis.core.domain.AbstractAuditingEntity;

/**
 * Represents a comment on a specific part of an exercise review.
 */
@Entity
@Table(name = "exercise_review_comment")
public class ExerciseReviewComment extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    @NotNull
    private ExerciseReview review;

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    @NotNull
    private ReviewCommentComponentType componentType;

    @Column(name = "path")
    @Size(max = 500)
    private String path;

    @Column(name = "line_number")
    private Integer line;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String body;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public ExerciseReview getReview() {
        return review;
    }

    public void setReview(ExerciseReview review) {
        this.review = review;
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

    public Instant getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Instant resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseReviewComment)) {
            return false;
        }
        return getId() != null && getId().equals(((ExerciseReviewComment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExerciseReviewComment{" + "id=" + getId() + ", reviewId=" + (getReview() != null ? getReview().getId() : "null") + ", componentType=" + getComponentType()
                + ", path='" + getPath() + '\'' + ", line=" + getLine() + ", resolvedAt=" + getResolvedAt() + ", updatedAt=" + getUpdatedAt() + '}';
    }
}
