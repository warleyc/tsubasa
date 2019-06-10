package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCombinationCutPosition} entity.
 */
public class MCombinationCutPositionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actionCutId;

    @NotNull
    private Integer characterId;

    @NotNull
    private Integer activatorPosition;

    @NotNull
    private Integer participantPosition1;

    @NotNull
    private Integer participantPosition2;

    @NotNull
    private Integer participantPosition3;

    @NotNull
    private Integer participantPosition4;

    @NotNull
    private Integer participantPosition5;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getActivatorPosition() {
        return activatorPosition;
    }

    public void setActivatorPosition(Integer activatorPosition) {
        this.activatorPosition = activatorPosition;
    }

    public Integer getParticipantPosition1() {
        return participantPosition1;
    }

    public void setParticipantPosition1(Integer participantPosition1) {
        this.participantPosition1 = participantPosition1;
    }

    public Integer getParticipantPosition2() {
        return participantPosition2;
    }

    public void setParticipantPosition2(Integer participantPosition2) {
        this.participantPosition2 = participantPosition2;
    }

    public Integer getParticipantPosition3() {
        return participantPosition3;
    }

    public void setParticipantPosition3(Integer participantPosition3) {
        this.participantPosition3 = participantPosition3;
    }

    public Integer getParticipantPosition4() {
        return participantPosition4;
    }

    public void setParticipantPosition4(Integer participantPosition4) {
        this.participantPosition4 = participantPosition4;
    }

    public Integer getParticipantPosition5() {
        return participantPosition5;
    }

    public void setParticipantPosition5(Integer participantPosition5) {
        this.participantPosition5 = participantPosition5;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mCharacterId) {
        this.idId = mCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCombinationCutPositionDTO mCombinationCutPositionDTO = (MCombinationCutPositionDTO) o;
        if (mCombinationCutPositionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCombinationCutPositionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCombinationCutPositionDTO{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", characterId=" + getCharacterId() +
            ", activatorPosition=" + getActivatorPosition() +
            ", participantPosition1=" + getParticipantPosition1() +
            ", participantPosition2=" + getParticipantPosition2() +
            ", participantPosition3=" + getParticipantPosition3() +
            ", participantPosition4=" + getParticipantPosition4() +
            ", participantPosition5=" + getParticipantPosition5() +
            ", id=" + getIdId() +
            "}";
    }
}
