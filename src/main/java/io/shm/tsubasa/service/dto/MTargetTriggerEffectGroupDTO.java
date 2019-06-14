package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetTriggerEffectGroup} entity.
 */
public class MTargetTriggerEffectGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer triggerEffectId;


    private Long mtriggereffectbaseId;

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

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public Long getMtriggereffectbaseId() {
        return mtriggereffectbaseId;
    }

    public void setMtriggereffectbaseId(Long mTriggerEffectBaseId) {
        this.mtriggereffectbaseId = mTriggerEffectBaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = (MTargetTriggerEffectGroupDTO) o;
        if (mTargetTriggerEffectGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetTriggerEffectGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetTriggerEffectGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", triggerEffectId=" + getTriggerEffectId() +
            ", mtriggereffectbase=" + getMtriggereffectbaseId() +
            "}";
    }
}
