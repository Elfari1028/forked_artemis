package de.tum.cit.aet.artemis.exercise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.tum.cit.aet.artemis.exercise.domain.versioning.ExerciseVersion;

/**
 * Spring Data JPA repository for the ExerciseVersion entity.
 */
@Repository
public interface ExerciseVersionRepository extends JpaRepository<ExerciseVersion, Long> {

    @Query("""
                SELECT ev FROM ExerciseVersion ev
                WHERE ev.exercise.id = :exerciseId
                ORDER BY ev.createdDate DESC
            """)
    List<ExerciseVersion> findAllVersions(@Param("exerciseId") Long exerciseId);

    @Query("""
                SELECT ev FROM ExerciseVersion ev
                WHERE ev.exercise.id = :exerciseId
                ORDER BY ev.createdDate DESC
                LIMIT 1
            """)
    Optional<ExerciseVersion> findLatestVersion(@Param("exerciseId") Long exerciseId);

    @Query("""
                SELECT ev FROM ExerciseVersion ev
                WHERE ev.id = :versionId
            """)
    Optional<ExerciseVersion> findVersionById(@Param("versionId") Long versionId);
}
