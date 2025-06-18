package de.tum.cit.aet.artemis.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReviewComment;

/**
 * Spring Data JPA repository for the ExerciseReviewComment entity.
 */
@Repository
public interface ExerciseReviewCommentRepository extends JpaRepository<ExerciseReviewComment, Long> {
}
