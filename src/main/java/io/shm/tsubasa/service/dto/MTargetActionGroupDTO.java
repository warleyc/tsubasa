package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetActionGroup} entity.
 */
public class MTargetActionGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer actionId;


    private Long mactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Long getMactionId() {
        return mactionId;
    }

    public void setMactionId(Long mActionId) {
        this.mactionId = mActionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetActionGroupDTO mTargetActionGroupDTO = (MTargetActionGroupDTO) o;
        if (mTargetActionGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetActionGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetActionGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", actionId=" + getActionId() +
            ", maction=" + getMactionId() +
            "}";
    }
}
