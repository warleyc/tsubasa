package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MSituationAnnounce} entity.
 */
public class MSituationAnnounceDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer situationId;

    @NotNull
    private Integer groupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSituationId() {
        return situationId;
    }

    public void setSituationId(Integer situationId) {
        this.situationId = situationId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MSituationAnnounceDTO mSituationAnnounceDTO = (MSituationAnnounceDTO) o;
        if (mSituationAnnounceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mSituationAnnounceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MSituationAnnounceDTO{" +
            "id=" + getId() +
            ", situationId=" + getSituationId() +
            ", groupId=" + getGroupId() +
            "}";
    }
}
