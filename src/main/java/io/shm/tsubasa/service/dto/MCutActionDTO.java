package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCutAction} entity.
 */
public class MCutActionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actionCutId;

    @NotNull
    private Integer cutActionType;

    
    @Lob
    private String cutSequenceText;


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

    public Integer getCutActionType() {
        return cutActionType;
    }

    public void setCutActionType(Integer cutActionType) {
        this.cutActionType = cutActionType;
    }

    public String getCutSequenceText() {
        return cutSequenceText;
    }

    public void setCutSequenceText(String cutSequenceText) {
        this.cutSequenceText = cutSequenceText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCutActionDTO mCutActionDTO = (MCutActionDTO) o;
        if (mCutActionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCutActionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCutActionDTO{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", cutActionType=" + getCutActionType() +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
