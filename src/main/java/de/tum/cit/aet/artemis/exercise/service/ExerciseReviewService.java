package de.tum.cit.aet.artemis.exercise.service;

import java.util.List;

import de.tum.cit.aet.artemis.core.domain.User;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReview;
import de.tum.cit.aet.artemis.exercise.dto.ExerciseReviewDTO;
import de.tum.cit.aet.artemis.exercise.dto.ReviewCommentDTO;

/**
 * Service Interface for managing ExerciseReview and related comments.
 */
public interface ExerciseReviewService {

    /**
     * Submit a single comment as a review.
     * This creates a new review with a single comment.
     *
     * @param exerciseVersionId the ID of the exercise version
     * @param commentDTO        the comment to submit
     * @param currentUser       the current user submitting the review
     * @return the created review
     */
    ExerciseReview submitSingleComment(Long exerciseVersionId, ReviewCommentDTO commentDTO, User currentUser);

    /**
     * Submit a review with multiple comments.
     *
     * @param reviewDTO   the review DTO containing the comments
     * @param currentUser the current user submitting the review
     * @return the created review with all comments
     */
    ExerciseReview submitReview(ExerciseReviewDTO reviewDTO, User currentUser);

    /**
     * Get all reviews for a specific exercise version.
     *
     * @param exerciseVersionId the ID of the exercise version
     * @return list of reviews with their comments
     */
    List<ExerciseReview> getReviewsForExerciseVersion(Long exerciseVersionId);

    /**
     * Get a single review by ID with its comments.
     *
     * @param reviewId the ID of the review
     * @return the review with its comments
     */
    ExerciseReview getReviewWithComments(Long reviewId);
}
