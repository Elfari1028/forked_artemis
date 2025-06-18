package de.tum.cit.aet.artemis.exercise.domain.versioning;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import de.tum.cit.aet.artemis.core.domain.AbstractAuditingEntity;
import de.tum.cit.aet.artemis.exercise.domain.Exercise;

/**
 * Represents a version of an exercise, storing the problem statement at a specific point in time.
 */
@Entity
@Table(name = "exercise_version")
public class ExerciseVersion extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    @NotNull
    private Exercise exercise;

    @Column(name = "problem_statement", columnDefinition = "TEXT")
    private String problemStatement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExerciseVersion)) {
            return false;
        }
        return getId() != null && getId().equals(((ExerciseVersion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExerciseVersion{" + "id=" + getId() + ", exerciseId=" + (getExercise() != null ? getExercise().getId() : "null") + ", createdDate=" + getCreatedDate() + '}';
    }
}
