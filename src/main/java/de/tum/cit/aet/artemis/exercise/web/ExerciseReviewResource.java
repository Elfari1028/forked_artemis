package de.tum.cit.aet.artemis.exercise.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tum.cit.aet.artemis.core.domain.User;
import de.tum.cit.aet.artemis.core.repository.UserRepository;
import de.tum.cit.aet.artemis.core.security.SecurityUtils;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReview;
import de.tum.cit.aet.artemis.exercise.dto.ExerciseReviewDTO;
import de.tum.cit.aet.artemis.exercise.dto.ReviewCommentDTO;
import de.tum.cit.aet.artemis.exercise.service.ExerciseReviewService;

/**
 * REST controller for managing exercise reviews.
 */
@RestController
@RequestMapping("/api")
public class ExerciseReviewResource {

    private final Logger log = LoggerFactory.getLogger(ExerciseReviewResource.class);

    private final ExerciseReviewService exerciseReviewService;

    private final UserRepository userRepository;

    public ExerciseReviewResource(ExerciseReviewService exerciseReviewService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.exerciseReviewService = exerciseReviewService;
    }

    /**
     * POST /exercise-reviews/comment : Submit a single comment as a review.
     *
     * @param exerciseVersionId the ID of the exercise version
     * @param commentDTO        the comment to submit
     * @return the ResponseEntity with status 201 (Created) and with body the new review,
     *         or with status 400 (Bad Request) if the exercise version ID is invalid
     */
    @PostMapping("/exercise-reviews/comment")
    public ResponseEntity<ExerciseReview> submitSingleComment(@RequestParam Long exerciseVersionId, @RequestBody ReviewCommentDTO commentDTO) throws URISyntaxException {
        log.debug("REST request to submit a single comment for exercise version: {}", exerciseVersionId);

        User currentUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithGroupsAndAuthoritiesByLogin)
                .orElseThrow(() -> new IllegalStateException("Current user not found"));

        ExerciseReview result = exerciseReviewService.submitSingleComment(exerciseVersionId, commentDTO, currentUser);

        return ResponseEntity.created(new URI("/api/exercise-reviews/" + result.getId())).body(result);
    }

    /**
     * POST /exercise-reviews : Submit a review with multiple comments.
     *
     * @param reviewDTO the review DTO containing the comments
     * @return the ResponseEntity with status 201 (Created) and with body the new review,
     *         or with status 400 (Bad Request) if the exercise version ID is invalid
     */
    @PostMapping("/exercise-reviews")
    public ResponseEntity<ExerciseReview> submitReview(@RequestBody ExerciseReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to submit a review with {} comments", reviewDTO.getComments().size());

        User currentUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithGroupsAndAuthoritiesByLogin)
                .orElseThrow(() -> new IllegalStateException("Current user not found"));

        ExerciseReview result = exerciseReviewService.submitReview(reviewDTO, currentUser);

        return ResponseEntity.created(new URI("/api/exercise-reviews/" + result.getId())).body(result);
    }

    /**
     * GET /exercise-reviews/exercise-version/{exerciseVersionId} : Get all reviews for an exercise version.
     *
     * @param exerciseVersionId the ID of the exercise version
     * @return the ResponseEntity with status 200 (OK) and the list of reviews in body
     */
    @GetMapping("/exercise-reviews/exercise-version/{exerciseVersionId}")
    public ResponseEntity<List<ExerciseReview>> getReviewsForExerciseVersion(@PathVariable Long exerciseVersionId) {
        log.debug("REST request to get reviews for exercise version: {}", exerciseVersionId);

        List<ExerciseReview> reviews = exerciseReviewService.getReviewsForExerciseVersion(exerciseVersionId);
        return ResponseEntity.ok().body(reviews);
    }

    /**
     * GET /exercise-reviews/{reviewId} : Get a review by ID with its comments.
     *
     * @param reviewId the ID of the review
     * @return the ResponseEntity with status 200 (OK) and the review in body,
     *         or with status 404 (Not Found) if the review doesn't exist
     */
    @GetMapping("/exercise-reviews/{reviewId}")
    public ResponseEntity<ExerciseReview> getReview(@PathVariable Long reviewId) {
        log.debug("REST request to get review: {}", reviewId);

        ExerciseReview review = exerciseReviewService.getReviewWithComments(reviewId);
        return ResponseEntity.ok().body(review);
    }
}
