package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMatchResultCutin} entity.
 */
public class MMatchResultCutinDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer characterId;

    @NotNull
    private Integer teamId;

    @NotNull
    private Integer isWin;

    
    @Lob
    private String text;

    
    @Lob
    private String soundEvent;


    private Long mcharacterId;

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

    public Integer getIsWin() {
        return isWin;
    }

    public void setIsWin(Integer isWin) {
        this.isWin = isWin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSoundEvent() {
        return soundEvent;
    }

    public void setSoundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
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

        MMatchResultCutinDTO mMatchResultCutinDTO = (MMatchResultCutinDTO) o;
        if (mMatchResultCutinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMatchResultCutinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMatchResultCutinDTO{" +
            "id=" + getId() +
            ", characterId=" + getCharacterId() +
            ", teamId=" + getTeamId() +
            ", isWin=" + getIsWin() +
            ", text='" + getText() + "'" +
            ", soundEvent='" + getSoundEvent() + "'" +
            ", mcharacter=" + getMcharacterId() +
            "}";
    }
}
