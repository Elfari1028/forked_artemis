package de.tum.cit.aet.artemis.exercise.domain;

import java.time.ZonedDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import de.tum.cit.aet.artemis.core.domain.AbstractAuditingEntity;
import de.tum.cit.aet.artemis.core.domain.User;

/**
 * An ExerciseReviewComment entity represents a comment within an exercise review.
 * It contains information about the component type, file path, line number, comment body,
 * and resolution status.
 */
@Entity
@Table(name = "exercise_review_comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExerciseReviewComment extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private ExerciseReview review;

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    private ComponentType componentType;

    @Column(name = "path")
    private String path;

    @Column(name = "line")
    private Integer line;

    @Column(name = "body", columnDefinition = "longtext", nullable = false)
    private String body;

    @Column(name = "resolved_at")
    @Nullable
    private ZonedDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public ExerciseReview getReview() {
        return review;
    }

    public void setReview(ExerciseReview review) {
        this.review = review;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
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

    @Nullable
    public ZonedDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(@Nullable ZonedDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExerciseReviewComment))
            return false;
        return this.getId() != null && this.getId().equals(((ExerciseReviewComment) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExerciseReviewComment{" + "id=" + this.getId() + ", componentType=" + componentType + ", path='" + path + '\'' + ", line=" + line + ", body='" + body + '\''
                + ", resolvedAt=" + resolvedAt + ", author=" + author.getLogin() + "}";
    }
}
