package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetCharacterGroup} entity.
 */
public class MTargetCharacterGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer characterId;


    private Long mcharacterId;

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

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Long getMcharacterId() {
        return mcharacterId;
    }

    public void setMcharacterId(Long mCharacterId) {
        this.mcharacterId = mCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = (MTargetCharacterGroupDTO) o;
        if (mTargetCharacterGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetCharacterGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetCharacterGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", characterId=" + getCharacterId() +
            ", mcharacter=" + getMcharacterId() +
            "}";
    }
}
