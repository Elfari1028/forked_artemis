package de.tum.cit.aet.artemis.exercise.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import de.tum.cit.aet.artemis.core.domain.AbstractAuditingEntity;
import de.tum.cit.aet.artemis.core.domain.User;

/**
 * An ExerciseReview entity represents a review for a specific exercise version.
 * It contains information about the reviewer (author), the exercise version being reviewed,
 * and when the review was submitted.
 */
@Entity
@Table(name = "exercise_review")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExerciseReview extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "exercise_version_id", nullable = false)
    private ExerciseVersion exerciseVersion;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "submitted_at")
    private ZonedDateTime submittedAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExerciseReviewComment> comments = new HashSet<>();

    public ExerciseVersion getExerciseVersion() {
        return exerciseVersion;
    }

    public void setExerciseVersion(ExerciseVersion exerciseVersion) {
        this.exerciseVersion = exerciseVersion;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ZonedDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(ZonedDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Set<ExerciseReviewComment> getComments() {
        return comments;
    }

    public void setComments(Set<ExerciseReviewComment> comments) {
        this.comments = comments;
    }

    public ExerciseReview addComment(ExerciseReviewComment comment) {
        this.comments.add(comment);
        comment.setReview(this);
        return this;
    }

    public ExerciseReview removeComment(ExerciseReviewComment comment) {
        this.comments.remove(comment);
        comment.setReview(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExerciseReview))
            return false;
        return this.getId() != null && this.getId().equals(((ExerciseReview) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExerciseReview{" + "id=" + this.getId() + ", exerciseVersion=" + exerciseVersion.getId() + ", author=" + author.getLogin() + ", submittedAt=" + submittedAt + "}";
    }
}
