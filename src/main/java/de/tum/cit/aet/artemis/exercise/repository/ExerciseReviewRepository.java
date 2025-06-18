package de.tum.cit.aet.artemis.exercise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReview;

/**
 * Spring Data JPA repository for the ExerciseReview entity.
 */
@Repository
public interface ExerciseReviewRepository extends JpaRepository<ExerciseReview, Long> {

    /**
     * Find all reviews for a specific exercise version.
     *
     * @param exerciseVersionId the ID of the exercise version
     * @return list of reviews
     */
    List<ExerciseReview> findByExerciseVersionId(Long exerciseVersionId);

    /**
     * Find a review by ID with its comments eagerly loaded.
     *
     * @param reviewId the ID of the review
     * @return the review with comments if found
     */
    @Query("SELECT r FROM ExerciseReview r LEFT JOIN FETCH r.comments WHERE r.id = :reviewId")
    Optional<ExerciseReview> findByIdWithComments(@Param("reviewId") Long reviewId);
}
