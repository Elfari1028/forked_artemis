package de.tum.cit.aet.artemis.exercise.domain;

import de.tum.cit.aet.artemis.core.domain.DomainObject;
import de.tum.cit.aet.artemis.core.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.ZonedDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "exercise_version")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DiscriminatorValue(value="E")
public class ExerciseVersion extends DomainObject {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "title")
    private String title;

    @Column(name = "problem_statement")
    private String problemStatement;

    @Column(name = "release_date")
    @Nullable
    private ZonedDateTime releaseDate;

    // TODO: Also use for quiz exercises
    @Column(name = "start_date")
    @Nullable
    private ZonedDateTime startDate;

    @Column(name = "due_date")
    @Nullable
    private ZonedDateTime dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private DifficultyLevel difficulty;

    @Column(name = "max_points", nullable = false)
    private Double maxPoints;

    @Column(name = "bonus_points")
    private Double bonusPoints = 0.0;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    @Nullable
    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@Nullable ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Nullable
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nullable ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    @Nullable
    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(@Nullable ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public Double getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Double maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Double getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }




}
