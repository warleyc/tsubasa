package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MActionSkillCutin.
 */
@Entity
@Table(name = "m_action_skill_cutin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MActionSkillCutin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action_cut_id", nullable = false)
    private Integer actionCutId;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    
    @Lob
    @Column(name = "cut_name", nullable = false)
    private String cutName;

    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "start_synchronize", nullable = false)
    private Integer startSynchronize;

    @NotNull
    @Column(name = "finish_synchronize", nullable = false)
    private Integer finishSynchronize;

    @NotNull
    @Column(name = "start_delay", nullable = false)
    private Integer startDelay;

    @Column(name = "chapter_1_character")
    private Integer chapter1Character;

    @Lob
    @Column(name = "chapter_1_text")
    private String chapter1Text;

    @Lob
    @Column(name = "chapter_1_sound_event")
    private String chapter1SoundEvent;

    @Column(name = "chapter_2_character")
    private Integer chapter2Character;

    @Lob
    @Column(name = "chapter_2_text")
    private String chapter2Text;

    @Lob
    @Column(name = "chapter_2_sound_event")
    private String chapter2SoundEvent;

    @Column(name = "chapter_3_character")
    private Integer chapter3Character;

    @Lob
    @Column(name = "chapter_3_text")
    private String chapter3Text;

    @Lob
    @Column(name = "chapter_3_sound_event")
    private String chapter3SoundEvent;

    @Column(name = "chapter_4_character")
    private Integer chapter4Character;

    @Lob
    @Column(name = "chapter_4_text")
    private String chapter4Text;

    @Lob
    @Column(name = "chapter_4_sound_event")
    private String chapter4SoundEvent;

    @Column(name = "chapter_5_character")
    private Integer chapter5Character;

    @Lob
    @Column(name = "chapter_5_text")
    private String chapter5Text;

    @Lob
    @Column(name = "chapter_5_sound_event")
    private String chapter5SoundEvent;

    @Lob
    @Column(name = "synchronize_text")
    private String synchronizeText;

    @Lob
    @Column(name = "synchronize_sound_event")
    private String synchronizeSoundEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public MActionSkillCutin actionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
        return this;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MActionSkillCutin characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCutName() {
        return cutName;
    }

    public MActionSkillCutin cutName(String cutName) {
        this.cutName = cutName;
        return this;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
    }

    public Integer getType() {
        return type;
    }

    public MActionSkillCutin type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStartSynchronize() {
        return startSynchronize;
    }

    public MActionSkillCutin startSynchronize(Integer startSynchronize) {
        this.startSynchronize = startSynchronize;
        return this;
    }

    public void setStartSynchronize(Integer startSynchronize) {
        this.startSynchronize = startSynchronize;
    }

    public Integer getFinishSynchronize() {
        return finishSynchronize;
    }

    public MActionSkillCutin finishSynchronize(Integer finishSynchronize) {
        this.finishSynchronize = finishSynchronize;
        return this;
    }

    public void setFinishSynchronize(Integer finishSynchronize) {
        this.finishSynchronize = finishSynchronize;
    }

    public Integer getStartDelay() {
        return startDelay;
    }

    public MActionSkillCutin startDelay(Integer startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public void setStartDelay(Integer startDelay) {
        this.startDelay = startDelay;
    }

    public Integer getChapter1Character() {
        return chapter1Character;
    }

    public MActionSkillCutin chapter1Character(Integer chapter1Character) {
        this.chapter1Character = chapter1Character;
        return this;
    }

    public void setChapter1Character(Integer chapter1Character) {
        this.chapter1Character = chapter1Character;
    }

    public String getChapter1Text() {
        return chapter1Text;
    }

    public MActionSkillCutin chapter1Text(String chapter1Text) {
        this.chapter1Text = chapter1Text;
        return this;
    }

    public void setChapter1Text(String chapter1Text) {
        this.chapter1Text = chapter1Text;
    }

    public String getChapter1SoundEvent() {
        return chapter1SoundEvent;
    }

    public MActionSkillCutin chapter1SoundEvent(String chapter1SoundEvent) {
        this.chapter1SoundEvent = chapter1SoundEvent;
        return this;
    }

    public void setChapter1SoundEvent(String chapter1SoundEvent) {
        this.chapter1SoundEvent = chapter1SoundEvent;
    }

    public Integer getChapter2Character() {
        return chapter2Character;
    }

    public MActionSkillCutin chapter2Character(Integer chapter2Character) {
        this.chapter2Character = chapter2Character;
        return this;
    }

    public void setChapter2Character(Integer chapter2Character) {
        this.chapter2Character = chapter2Character;
    }

    public String getChapter2Text() {
        return chapter2Text;
    }

    public MActionSkillCutin chapter2Text(String chapter2Text) {
        this.chapter2Text = chapter2Text;
        return this;
    }

    public void setChapter2Text(String chapter2Text) {
        this.chapter2Text = chapter2Text;
    }

    public String getChapter2SoundEvent() {
        return chapter2SoundEvent;
    }

    public MActionSkillCutin chapter2SoundEvent(String chapter2SoundEvent) {
        this.chapter2SoundEvent = chapter2SoundEvent;
        return this;
    }

    public void setChapter2SoundEvent(String chapter2SoundEvent) {
        this.chapter2SoundEvent = chapter2SoundEvent;
    }

    public Integer getChapter3Character() {
        return chapter3Character;
    }

    public MActionSkillCutin chapter3Character(Integer chapter3Character) {
        this.chapter3Character = chapter3Character;
        return this;
    }

    public void setChapter3Character(Integer chapter3Character) {
        this.chapter3Character = chapter3Character;
    }

    public String getChapter3Text() {
        return chapter3Text;
    }

    public MActionSkillCutin chapter3Text(String chapter3Text) {
        this.chapter3Text = chapter3Text;
        return this;
    }

    public void setChapter3Text(String chapter3Text) {
        this.chapter3Text = chapter3Text;
    }

    public String getChapter3SoundEvent() {
        return chapter3SoundEvent;
    }

    public MActionSkillCutin chapter3SoundEvent(String chapter3SoundEvent) {
        this.chapter3SoundEvent = chapter3SoundEvent;
        return this;
    }

    public void setChapter3SoundEvent(String chapter3SoundEvent) {
        this.chapter3SoundEvent = chapter3SoundEvent;
    }

    public Integer getChapter4Character() {
        return chapter4Character;
    }

    public MActionSkillCutin chapter4Character(Integer chapter4Character) {
        this.chapter4Character = chapter4Character;
        return this;
    }

    public void setChapter4Character(Integer chapter4Character) {
        this.chapter4Character = chapter4Character;
    }

    public String getChapter4Text() {
        return chapter4Text;
    }

    public MActionSkillCutin chapter4Text(String chapter4Text) {
        this.chapter4Text = chapter4Text;
        return this;
    }

    public void setChapter4Text(String chapter4Text) {
        this.chapter4Text = chapter4Text;
    }

    public String getChapter4SoundEvent() {
        return chapter4SoundEvent;
    }

    public MActionSkillCutin chapter4SoundEvent(String chapter4SoundEvent) {
        this.chapter4SoundEvent = chapter4SoundEvent;
        return this;
    }

    public void setChapter4SoundEvent(String chapter4SoundEvent) {
        this.chapter4SoundEvent = chapter4SoundEvent;
    }

    public Integer getChapter5Character() {
        return chapter5Character;
    }

    public MActionSkillCutin chapter5Character(Integer chapter5Character) {
        this.chapter5Character = chapter5Character;
        return this;
    }

    public void setChapter5Character(Integer chapter5Character) {
        this.chapter5Character = chapter5Character;
    }

    public String getChapter5Text() {
        return chapter5Text;
    }

    public MActionSkillCutin chapter5Text(String chapter5Text) {
        this.chapter5Text = chapter5Text;
        return this;
    }

    public void setChapter5Text(String chapter5Text) {
        this.chapter5Text = chapter5Text;
    }

    public String getChapter5SoundEvent() {
        return chapter5SoundEvent;
    }

    public MActionSkillCutin chapter5SoundEvent(String chapter5SoundEvent) {
        this.chapter5SoundEvent = chapter5SoundEvent;
        return this;
    }

    public void setChapter5SoundEvent(String chapter5SoundEvent) {
        this.chapter5SoundEvent = chapter5SoundEvent;
    }

    public String getSynchronizeText() {
        return synchronizeText;
    }

    public MActionSkillCutin synchronizeText(String synchronizeText) {
        this.synchronizeText = synchronizeText;
        return this;
    }

    public void setSynchronizeText(String synchronizeText) {
        this.synchronizeText = synchronizeText;
    }

    public String getSynchronizeSoundEvent() {
        return synchronizeSoundEvent;
    }

    public MActionSkillCutin synchronizeSoundEvent(String synchronizeSoundEvent) {
        this.synchronizeSoundEvent = synchronizeSoundEvent;
        return this;
    }

    public void setSynchronizeSoundEvent(String synchronizeSoundEvent) {
        this.synchronizeSoundEvent = synchronizeSoundEvent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MActionSkillCutin)) {
            return false;
        }
        return id != null && id.equals(((MActionSkillCutin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MActionSkillCutin{" +
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
