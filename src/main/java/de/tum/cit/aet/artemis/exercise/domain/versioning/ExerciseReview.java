package de.tum.cit.aet.artemis.exercise.domain.versioning;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import de.tum.cit.aet.artemis.core.domain.AbstractAuditingEntity;
import de.tum.cit.aet.artemis.core.domain.User;

/**
 * Represents a review for a specific version of an exercise.
 */
@Entity
@Table(name = "exercise_review")
public class ExerciseReview extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_version_id", nullable = false)
    @NotNull
    private ExerciseVersion exerciseVersion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull
    private User author;

    @Column(name = "submitted_at", nullable = false)
    @NotNull
    private Instant submittedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public ExerciseVersion getExerciseVersion() {
        return this.exerciseVersion;
    }

    public void setExerciseVersion(ExerciseVersion exerciseVersion) {
        this.exerciseVersion = exerciseVersion;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Instant submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseReview)) {
            return false;
        }
        return getId() != null && getId().equals(((ExerciseReview) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExerciseReview{" + "id=" + getId() + ", exerciseVersionId=" + (getExerciseVersion() != null ? getExerciseVersion().getId() : "null") + ", authorId="
                + (getAuthor() != null ? getAuthor().getId() : "null") + ", submittedAt=" + getSubmittedAt() + '}';
    }
}
