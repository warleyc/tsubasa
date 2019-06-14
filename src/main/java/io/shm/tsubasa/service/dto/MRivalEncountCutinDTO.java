package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MRivalEncountCutin} entity.
 */
public class MRivalEncountCutinDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer offenseCharacterId;

    private Integer offenseTeamId;

    @NotNull
    private Integer defenseCharacterId;

    private Integer defenseTeamId;

    @NotNull
    private Integer cutinType;

    @Lob
    private String chapter1Text;

    @Lob
    private String chapter1SoundEvent;

    @Lob
    private String chapter2Text;

    @Lob
    private String chapter2SoundEvent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOffenseCharacterId() {
        return offenseCharacterId;
    }

    public void setOffenseCharacterId(Integer offenseCharacterId) {
        this.offenseCharacterId = offenseCharacterId;
    }

    public Integer getOffenseTeamId() {
        return offenseTeamId;
    }

    public void setOffenseTeamId(Integer offenseTeamId) {
        this.offenseTeamId = offenseTeamId;
    }

    public Integer getDefenseCharacterId() {
        return defenseCharacterId;
    }

    public void setDefenseCharacterId(Integer defenseCharacterId) {
        this.defenseCharacterId = defenseCharacterId;
    }

    public Integer getDefenseTeamId() {
        return defenseTeamId;
    }

    public void setDefenseTeamId(Integer defenseTeamId) {
        this.defenseTeamId = defenseTeamId;
    }

    public Integer getCutinType() {
        return cutinType;
    }

    public void setCutinType(Integer cutinType) {
        this.cutinType = cutinType;
    }

    public String getChapter1Text() {
        return chapter1Text;
    }

    public void setChapter1Text(String chapter1Text) {
        this.chapter1Text = chapter1Text;
    }

    public String getChapter1SoundEvent() {
        return chapter1SoundEvent;
    }

    public void setChapter1SoundEvent(String chapter1SoundEvent) {
        this.chapter1SoundEvent = chapter1SoundEvent;
    }

    public String getChapter2Text() {
        return chapter2Text;
    }

    public void setChapter2Text(String chapter2Text) {
        this.chapter2Text = chapter2Text;
    }

    public String getChapter2SoundEvent() {
        return chapter2SoundEvent;
    }

    public void setChapter2SoundEvent(String chapter2SoundEvent) {
        this.chapter2SoundEvent = chapter2SoundEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MRivalEncountCutinDTO mRivalEncountCutinDTO = (MRivalEncountCutinDTO) o;
        if (mRivalEncountCutinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mRivalEncountCutinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MRivalEncountCutinDTO{" +
            "id=" + getId() +
            ", offenseCharacterId=" + getOffenseCharacterId() +
            ", offenseTeamId=" + getOffenseTeamId() +
            ", defenseCharacterId=" + getDefenseCharacterId() +
            ", defenseTeamId=" + getDefenseTeamId() +
            ", cutinType=" + getCutinType() +
            ", chapter1Text='" + getChapter1Text() + "'" +
            ", chapter1SoundEvent='" + getChapter1SoundEvent() + "'" +
            ", chapter2Text='" + getChapter2Text() + "'" +
            ", chapter2SoundEvent='" + getChapter2SoundEvent() + "'" +
            "}";
    }
}
