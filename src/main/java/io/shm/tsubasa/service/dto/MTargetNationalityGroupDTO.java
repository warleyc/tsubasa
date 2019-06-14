package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetNationalityGroup} entity.
 */
public class MTargetNationalityGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer nationalityId;


    private Long mnationalityId;

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

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getMnationalityId() {
        return mnationalityId;
    }

    public void setMnationalityId(Long mNationalityId) {
        this.mnationalityId = mNationalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = (MTargetNationalityGroupDTO) o;
        if (mTargetNationalityGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetNationalityGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetNationalityGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", nationalityId=" + getNationalityId() +
            ", mnationality=" + getMnationalityId() +
            "}";
    }
}
