package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetFormationGroup} entity.
 */
public class MTargetFormationGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer formationId;


    private Long mformationId;

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

    public Integer getFormationId() {
        return formationId;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public Long getMformationId() {
        return mformationId;
    }

    public void setMformationId(Long mFormationId) {
        this.mformationId = mFormationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetFormationGroupDTO mTargetFormationGroupDTO = (MTargetFormationGroupDTO) o;
        if (mTargetFormationGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetFormationGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetFormationGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", formationId=" + getFormationId() +
            ", mformation=" + getMformationId() +
            "}";
    }
}
