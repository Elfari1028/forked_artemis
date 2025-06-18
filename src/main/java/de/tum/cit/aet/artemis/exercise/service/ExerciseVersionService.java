package de.tum.cit.aet.artemis.exercise.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.tum.cit.aet.artemis.exercise.domain.Exercise;
import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseVersion;
import de.tum.cit.aet.artemis.exercise.dto.ExerciseVersionDTO;
import de.tum.cit.aet.artemis.exercise.repository.ExerciseVersionRepository;

/**
 * Service Implementation for managing {@link ExerciseVersion}.
 */
@Service
@Transactional
public class ExerciseVersionService {

    private final ExerciseVersionRepository exerciseVersionRepository;

    public ExerciseVersionService(ExerciseVersionRepository exerciseVersionRepository) {
        this.exerciseVersionRepository = exerciseVersionRepository;
    }

    /**
     * Save an exercise version.
     *
     * @param exerciseVersionDTO the DTO to save.
     * @return the persisted DTO.
     */
    @Transactional
    public ExerciseVersionDTO save(ExerciseVersionDTO exerciseVersionDTO) {
        ExerciseVersion exerciseVersion = new ExerciseVersion();

        // Set the exercise reference
        Exercise exercise = new Exercise();
        exercise.setId(exerciseVersionDTO.getExerciseId());
        exerciseVersion.setExercise(exercise);

        // Set the problem statement
        exerciseVersion.setProblemStatement(exerciseVersionDTO.getProblemStatement());

        // Save the entity
        exerciseVersion = exerciseVersionRepository.save(exerciseVersion);
        return ExerciseVersionDTO.of(exerciseVersion);
    }

    /**
     * Create and save a new version of an exercise.
     *
     * @param exercise the exercise to create a version for.
     * @return the created version DTO.
     */
    @Transactional
    public ExerciseVersionDTO createVersion(Exercise exercise) {
        ExerciseVersion version = new ExerciseVersion();
        version.setExercise(exercise);
        version.setProblemStatement(exercise.getProblemStatement());

        version = exerciseVersionRepository.save(version);
        return ExerciseVersionDTO.of(version);
    }

    /**
     * Get all versions for an exercise, ordered by creation date (newest first).
     *
     * @param exerciseId the ID of the exercise.
     * @return the list of version DTOs.
     */
    public List<ExerciseVersionDTO> findAllVersionsForExercise(Long exerciseId) {
        return exerciseVersionRepository.findAllVersions(exerciseId).stream().map(ExerciseVersionDTO::of).collect(Collectors.toList());
    }

    /**
     * Get the latest version of an exercise.
     *
     * @param exerciseId the ID of the exercise.
     * @return an Optional containing the latest version DTO if it exists, or an empty Optional if not.
     */
    public Optional<ExerciseVersionDTO> findLatestVersion(Long exerciseId) {
        return exerciseVersionRepository.findLatestVersion(exerciseId).map(ExerciseVersionDTO::of);
    }

    /**
     * Get a specific version of an exercise by its ID.
     *
     * @param versionId the ID of the version to find.
     * @return an Optional containing the version DTO if it exists, or an empty Optional if not found.
     */
    public Optional<ExerciseVersionDTO> findVersionById(Long versionId) {
        return exerciseVersionRepository.findVersionById(versionId).map(ExerciseVersionDTO::of);
    }

}
