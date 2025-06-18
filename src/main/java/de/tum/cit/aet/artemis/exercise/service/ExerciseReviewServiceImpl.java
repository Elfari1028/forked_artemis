package de.tum.cit.aet.artemis.exercise.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.tum.cit.aet.artemis.core.domain.User;
import de.tum.cit.aet.artemis.core.repository.UserRepository;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReview;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseReviewComment;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseVersion;
import de.tum.cit.aet.artemis.exercise.dto.ExerciseReviewDTO;
import de.tum.cit.aet.artemis.exercise.dto.ReviewCommentDTO;
import de.tum.cit.aet.artemis.exercise.repository.ExerciseReviewCommentRepository;
import de.tum.cit.aet.artemis.exercise.repository.ExerciseReviewRepository;
import de.tum.cit.aet.artemis.exercise.repository.ExerciseVersionRepository;

/**
 * Service Implementation for managing ExerciseReview and related comments.
 */
@Service
@Transactional
public class ExerciseReviewServiceImpl implements ExerciseReviewService {

    private final Logger log = LoggerFactory.getLogger(ExerciseReviewServiceImpl.class);

    private final ExerciseReviewRepository exerciseReviewRepository;

    private final ExerciseReviewCommentRepository exerciseReviewCommentRepository;

    private final ExerciseVersionRepository exerciseVersionRepository;

    private final UserRepository userRepository;

    public ExerciseReviewServiceImpl(ExerciseReviewRepository exerciseReviewRepository, ExerciseReviewCommentRepository exerciseReviewCommentRepository,
            ExerciseVersionRepository exerciseVersionRepository, UserRepository userRepository) {
        this.exerciseReviewRepository = exerciseReviewRepository;
        this.exerciseReviewCommentRepository = exerciseReviewCommentRepository;
        this.exerciseVersionRepository = exerciseVersionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExerciseReview submitSingleComment(Long exerciseVersionId, ReviewCommentDTO commentDTO, User currentUser) {
        log.debug("Request to submit single comment for exercise version: {}", exerciseVersionId);

        ExerciseReview review = new ExerciseReview();
        ExerciseVersion exerciseVersion = exerciseVersionRepository.findById(exerciseVersionId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise version not found: " + exerciseVersionId));

        review.setExerciseVersion(exerciseVersion);
        review.setAuthor(currentUser);
        review.setSubmittedAt(Instant.now());

        ExerciseReview savedReview = exerciseReviewRepository.save(review);

        // Create and save the comment
        ExerciseReviewComment comment = createCommentFromDTO(commentDTO, savedReview);
        exerciseReviewCommentRepository.save(comment);

        savedReview.getComments().add(comment);
        return savedReview;
    }

    @Override
    public ExerciseReview submitReview(ExerciseReviewDTO reviewDTO, User currentUser) {
        log.debug("Request to submit review with {} comments", reviewDTO.getComments().size());

        ExerciseVersion exerciseVersion = exerciseVersionRepository.findById(reviewDTO.getExerciseVersionId())
                .orElseThrow(() -> new IllegalArgumentException("Exercise version not found: " + reviewDTO.getExerciseVersionId()));

        ExerciseReview review = new ExerciseReview();
        review.setExerciseVersion(exerciseVersion);
        review.setAuthor(currentUser);
        review.setSubmittedAt(Instant.now());

        ExerciseReview savedReview = exerciseReviewRepository.save(review);

        // Create and save all comments
        List<ExerciseReviewComment> comments = reviewDTO.getComments().stream().map(commentDTO -> createCommentFromDTO(commentDTO, savedReview)).collect(Collectors.toList());

        exerciseReviewCommentRepository.saveAll(comments);
        savedReview.setComments(comments);

        return savedReview;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseReview> getReviewsForExerciseVersion(Long exerciseVersionId) {
        log.debug("Request to get all reviews for exercise version: {}", exerciseVersionId);
        return exerciseReviewRepository.findByExerciseVersionId(exerciseVersionId);
    }

    @Override
    @Transactional(readOnly = true)
    public ExerciseReview getReviewWithComments(Long reviewId) {
        log.debug("Request to get review with comments: {}", reviewId);
        return exerciseReviewRepository.findByIdWithComments(reviewId).orElseThrow(() -> new IllegalArgumentException("Review not found: " + reviewId));
    }

    private ExerciseReviewComment createCommentFromDTO(ReviewCommentDTO dto, ExerciseReview review) {
        ExerciseReviewComment comment = new ExerciseReviewComment();
        comment.setReview(review);
        comment.setComponentType(dto.getComponentType());
        comment.setPath(dto.getPath());
        comment.setLine(dto.getLine());
        comment.setBody(dto.getBody());
        comment.setUpdatedAt(Instant.now());
        return comment;
    }
}
