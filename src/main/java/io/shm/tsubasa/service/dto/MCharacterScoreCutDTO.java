package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCharacterScoreCut} entity.
 */
public class MCharacterScoreCutDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer characterId;

    @NotNull
    private Integer teamId;

    @NotNull
    private Integer scoreCutType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getScoreCutType() {
        return scoreCutType;
    }

    public void setScoreCutType(Integer scoreCutType) {
        this.scoreCutType = scoreCutType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCharacterScoreCutDTO mCharacterScoreCutDTO = (MCharacterScoreCutDTO) o;
        if (mCharacterScoreCutDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCharacterScoreCutDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCharacterScoreCutDTO{" +
            "id=" + getId() +
            ", characterId=" + getCharacterId() +
            ", teamId=" + getTeamId() +
            ", scoreCutType=" + getScoreCutType() +
            "}";
    }
}
