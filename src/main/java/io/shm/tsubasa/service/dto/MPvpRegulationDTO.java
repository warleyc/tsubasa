package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPvpRegulation} entity.
 */
public class MPvpRegulationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;

    @NotNull
    private Integer matchOptionId;

    @NotNull
    private Integer deckConditionId;

    @NotNull
    private Integer ruleTutorialId;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getMatchOptionId() {
        return matchOptionId;
    }

    public void setMatchOptionId(Integer matchOptionId) {
        this.matchOptionId = matchOptionId;
    }

    public Integer getDeckConditionId() {
        return deckConditionId;
    }

    public void setDeckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public Integer getRuleTutorialId() {
        return ruleTutorialId;
    }

    public void setRuleTutorialId(Integer ruleTutorialId) {
        this.ruleTutorialId = ruleTutorialId;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mMatchOptionId) {
        this.idId = mMatchOptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPvpRegulationDTO mPvpRegulationDTO = (MPvpRegulationDTO) o;
        if (mPvpRegulationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPvpRegulationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPvpRegulationDTO{" +
            "id=" + getId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", matchOptionId=" + getMatchOptionId() +
            ", deckConditionId=" + getDeckConditionId() +
            ", ruleTutorialId=" + getRuleTutorialId() +
            ", id=" + getIdId() +
            "}";
    }
}
