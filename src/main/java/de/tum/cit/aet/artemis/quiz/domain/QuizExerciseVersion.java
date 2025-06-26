package de.tum.cit.aet.artemis.quiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.tum.cit.aet.artemis.exercise.domain.ExerciseVersion;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Q")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuizExerciseVersion extends ExerciseVersion {

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "is_open_for_practice")
    private Boolean isOpenForPractice;

    @Column(name = "allowed_number_of_attempts")
    private Integer allowedNumberOfAttempts;

    @Column(name = "randomize_question_order")
    private Boolean randomizeQuestionOrder;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getOpenForPractice() {
        return isOpenForPractice;
    }

    public void setOpenForPractice(Boolean openForPractice) {
        isOpenForPractice = openForPractice;
    }

    public Integer getAllowedNumberOfAttempts() {
        return allowedNumberOfAttempts;
    }

    public void setAllowedNumberOfAttempts(Integer allowedNumberOfAttempts) {
        this.allowedNumberOfAttempts = allowedNumberOfAttempts;
    }

    public Boolean getRandomizeQuestionOrder() {
        return randomizeQuestionOrder;
    }

    public void setRandomizeQuestionOrder(Boolean randomizeQuestionOrder) {
        this.randomizeQuestionOrder = randomizeQuestionOrder;
    }
}
