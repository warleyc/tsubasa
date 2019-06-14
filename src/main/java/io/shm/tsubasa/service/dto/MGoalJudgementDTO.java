package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGoalJudgement} entity.
 */
public class MGoalJudgementDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer judgementId;

    @NotNull
    private Integer encountersType;

    @NotNull
    private Integer successRate;

    @NotNull
    private Integer goalPostRate;

    @NotNull
    private Integer ballPushRate;

    @NotNull
    private Integer clearRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJudgementId() {
        return judgementId;
    }

    public void setJudgementId(Integer judgementId) {
        this.judgementId = judgementId;
    }

    public Integer getEncountersType() {
        return encountersType;
    }

    public void setEncountersType(Integer encountersType) {
        this.encountersType = encountersType;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getGoalPostRate() {
        return goalPostRate;
    }

    public void setGoalPostRate(Integer goalPostRate) {
        this.goalPostRate = goalPostRate;
    }

    public Integer getBallPushRate() {
        return ballPushRate;
    }

    public void setBallPushRate(Integer ballPushRate) {
        this.ballPushRate = ballPushRate;
    }

    public Integer getClearRate() {
        return clearRate;
    }

    public void setClearRate(Integer clearRate) {
        this.clearRate = clearRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGoalJudgementDTO mGoalJudgementDTO = (MGoalJudgementDTO) o;
        if (mGoalJudgementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGoalJudgementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGoalJudgementDTO{" +
            "id=" + getId() +
            ", judgementId=" + getJudgementId() +
            ", encountersType=" + getEncountersType() +
            ", successRate=" + getSuccessRate() +
            ", goalPostRate=" + getGoalPostRate() +
            ", ballPushRate=" + getBallPushRate() +
            ", clearRate=" + getClearRate() +
            "}";
    }
}
