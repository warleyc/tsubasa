package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MKeeperCutAction} entity.
 */
public class MKeeperCutActionDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actionCutId;

    @NotNull
    private Integer keeperCutActionType;

    
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

    public Integer getKeeperCutActionType() {
        return keeperCutActionType;
    }

    public void setKeeperCutActionType(Integer keeperCutActionType) {
        this.keeperCutActionType = keeperCutActionType;
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

        MKeeperCutActionDTO mKeeperCutActionDTO = (MKeeperCutActionDTO) o;
        if (mKeeperCutActionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mKeeperCutActionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MKeeperCutActionDTO{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", keeperCutActionType=" + getKeeperCutActionType() +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
