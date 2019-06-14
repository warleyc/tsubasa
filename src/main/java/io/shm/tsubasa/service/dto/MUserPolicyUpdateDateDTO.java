package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MUserPolicyUpdateDate} entity.
 */
public class MUserPolicyUpdateDateDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = (MUserPolicyUpdateDateDTO) o;
        if (mUserPolicyUpdateDateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mUserPolicyUpdateDateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MUserPolicyUpdateDateDTO{" +
            "id=" + getId() +
            ", updateDate=" + getUpdateDate() +
            "}";
    }
}
