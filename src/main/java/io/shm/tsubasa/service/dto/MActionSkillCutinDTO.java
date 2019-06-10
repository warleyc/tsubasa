package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MActionSkillCutin} entity.
 */
public class MActionSkillCutinDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer actionCutId;

    @NotNull
    private Integer characterId;

    
    @Lob
    private String cutName;

    @NotNull
    private Integer type;

    @NotNull
    private Integer startSynchronize;

    @NotNull
    private Integer finishSynchronize;

    @NotNull
    private Integer startDelay;

    private Integer chapter1Character;

    @Lob
    private String chapter1Text;

    @Lob
    private String chapter1SoundEvent;

    private Integer chapter2Character;

    @Lob
    private String chapter2Text;

    @Lob
    private String chapter2SoundEvent;

    private Integer chapter3Character;

    @Lob
    private String chapter3Text;

    @Lob
    private String chapter3SoundEvent;

    private Integer chapter4Character;

    @Lob
    private String chapter4Text;

    @Lob
    private String chapter4SoundEvent;

    private Integer chapter5Character;

    @Lob
    private String chapter5Text;

    @Lob
    private String chapter5SoundEvent;

    @Lob
    private String synchronizeText;

    @Lob
    private String synchronizeSoundEvent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCutName() {
        return cutName;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStartSynchronize() {
        return startSynchronize;
    }

    public void setStartSynchronize(Integer startSynchronize) {
        this.startSynchronize = startSynchronize;
    }

    public Integer getFinishSynchronize() {
        return finishSynchronize;
    }

    public void setFinishSynchronize(Integer finishSynchronize) {
        this.finishSynchronize = finishSynchronize;
    }

    public Integer getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(Integer startDelay) {
        this.startDelay = startDelay;
    }

    public Integer getChapter1Character() {
        return chapter1Character;
    }

    public void setChapter1Character(Integer chapter1Character) {
        this.chapter1Character = chapter1Character;
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

    public Integer getChapter2Character() {
        return chapter2Character;
    }

    public void setChapter2Character(Integer chapter2Character) {
        this.chapter2Character = chapter2Character;
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

    public Integer getChapter3Character() {
        return chapter3Character;
    }

    public void setChapter3Character(Integer chapter3Character) {
        this.chapter3Character = chapter3Character;
    }

    public String getChapter3Text() {
        return chapter3Text;
    }

    public void setChapter3Text(String chapter3Text) {
        this.chapter3Text = chapter3Text;
    }

    public String getChapter3SoundEvent() {
        return chapter3SoundEvent;
    }

    public void setChapter3SoundEvent(String chapter3SoundEvent) {
        this.chapter3SoundEvent = chapter3SoundEvent;
    }

    public Integer getChapter4Character() {
        return chapter4Character;
    }

    public void setChapter4Character(Integer chapter4Character) {
        this.chapter4Character = chapter4Character;
    }

    public String getChapter4Text() {
        return chapter4Text;
    }

    public void setChapter4Text(String chapter4Text) {
        this.chapter4Text = chapter4Text;
    }

    public String getChapter4SoundEvent() {
        return chapter4SoundEvent;
    }

    public void setChapter4SoundEvent(String chapter4SoundEvent) {
        this.chapter4SoundEvent = chapter4SoundEvent;
    }

    public Integer getChapter5Character() {
        return chapter5Character;
    }

    public void setChapter5Character(Integer chapter5Character) {
        this.chapter5Character = chapter5Character;
    }

    public String getChapter5Text() {
        return chapter5Text;
    }

    public void setChapter5Text(String chapter5Text) {
        this.chapter5Text = chapter5Text;
    }

    public String getChapter5SoundEvent() {
        return chapter5SoundEvent;
    }

    public void setChapter5SoundEvent(String chapter5SoundEvent) {
        this.chapter5SoundEvent = chapter5SoundEvent;
    }

    public String getSynchronizeText() {
        return synchronizeText;
    }

    public void setSynchronizeText(String synchronizeText) {
        this.synchronizeText = synchronizeText;
    }

    public String getSynchronizeSoundEvent() {
        return synchronizeSoundEvent;
    }

    public void setSynchronizeSoundEvent(String synchronizeSoundEvent) {
        this.synchronizeSoundEvent = synchronizeSoundEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MActionSkillCutinDTO mActionSkillCutinDTO = (MActionSkillCutinDTO) o;
        if (mActionSkillCutinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mActionSkillCutinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MActionSkillCutinDTO{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", characterId=" + getCharacterId() +
            ", cutName='" + getCutName() + "'" +
            ", type=" + getType() +
            ", startSynchronize=" + getStartSynchronize() +
            ", finishSynchronize=" + getFinishSynchronize() +
            ", startDelay=" + getStartDelay() +
            ", chapter1Character=" + getChapter1Character() +
            ", chapter1Text='" + getChapter1Text() + "'" +
            ", chapter1SoundEvent='" + getChapter1SoundEvent() + "'" +
            ", chapter2Character=" + getChapter2Character() +
            ", chapter2Text='" + getChapter2Text() + "'" +
            ", chapter2SoundEvent='" + getChapter2SoundEvent() + "'" +
            ", chapter3Character=" + getChapter3Character() +
            ", chapter3Text='" + getChapter3Text() + "'" +
            ", chapter3SoundEvent='" + getChapter3SoundEvent() + "'" +
            ", chapter4Character=" + getChapter4Character() +
            ", chapter4Text='" + getChapter4Text() + "'" +
            ", chapter4SoundEvent='" + getChapter4SoundEvent() + "'" +
            ", chapter5Character=" + getChapter5Character() +
            ", chapter5Text='" + getChapter5Text() + "'" +
            ", chapter5SoundEvent='" + getChapter5SoundEvent() + "'" +
            ", synchronizeText='" + getSynchronizeText() + "'" +
            ", synchronizeSoundEvent='" + getSynchronizeSoundEvent() + "'" +
            "}";
    }
}
