package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSituation} entity.
 */
public class MSituationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer kickoff;

    @NotNull
    private Integer penaltyKick;

    @NotNull
    private Integer matchInterval;

    @NotNull
    private Integer outOfPlay;

    @NotNull
    private Integer foul;

    @NotNull
    private Integer goal;

    @NotNull
    private Integer score;

    @NotNull
    private Integer time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKickoff() {
        return kickoff;
    }

    public void setKickoff(Integer kickoff) {
        this.kickoff = kickoff;
    }

    public Integer getPenaltyKick() {
        return penaltyKick;
    }

    public void setPenaltyKick(Integer penaltyKick) {
        this.penaltyKick = penaltyKick;
    }

    public Integer getMatchInterval() {
        return matchInterval;
    }

    public void setMatchInterval(Integer matchInterval) {
        this.matchInterval = matchInterval;
    }

    public Integer getOutOfPlay() {
        return outOfPlay;
    }

    public void setOutOfPlay(Integer outOfPlay) {
        this.outOfPlay = outOfPlay;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSituationDTO mSituationDTO = (MSituationDTO) o;
        if (mSituationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSituationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSituationDTO{" +
            "id=" + getId() +
            ", kickoff=" + getKickoff() +
            ", penaltyKick=" + getPenaltyKick() +
            ", matchInterval=" + getMatchInterval() +
            ", outOfPlay=" + getOutOfPlay() +
            ", foul=" + getFoul() +
            ", goal=" + getGoal() +
            ", score=" + getScore() +
            ", time=" + getTime() +
            "}";
    }
}
