package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRivalEncountCutin.
 */
@Entity
@Table(name = "m_rival_encount_cutin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRivalEncountCutin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "offense_character_id", nullable = false)
    private Integer offenseCharacterId;

    @Column(name = "offense_team_id")
    private Integer offenseTeamId;

    @NotNull
    @Column(name = "defense_character_id", nullable = false)
    private Integer defenseCharacterId;

    @Column(name = "defense_team_id")
    private Integer defenseTeamId;

    @NotNull
    @Column(name = "cutin_type", nullable = false)
    private Integer cutinType;

    @Lob
    @Column(name = "chapter_1_text")
    private String chapter1Text;

    @Lob
    @Column(name = "chapter_1_sound_event")
    private String chapter1SoundEvent;

    @Lob
    @Column(name = "chapter_2_text")
    private String chapter2Text;

    @Lob
    @Column(name = "chapter_2_sound_event")
    private String chapter2SoundEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOffenseCharacterId() {
        return offenseCharacterId;
    }

    public MRivalEncountCutin offenseCharacterId(Integer offenseCharacterId) {
        this.offenseCharacterId = offenseCharacterId;
        return this;
    }

    public void setOffenseCharacterId(Integer offenseCharacterId) {
        this.offenseCharacterId = offenseCharacterId;
    }

    public Integer getOffenseTeamId() {
        return offenseTeamId;
    }

    public MRivalEncountCutin offenseTeamId(Integer offenseTeamId) {
        this.offenseTeamId = offenseTeamId;
        return this;
    }

    public void setOffenseTeamId(Integer offenseTeamId) {
        this.offenseTeamId = offenseTeamId;
    }

    public Integer getDefenseCharacterId() {
        return defenseCharacterId;
    }

    public MRivalEncountCutin defenseCharacterId(Integer defenseCharacterId) {
        this.defenseCharacterId = defenseCharacterId;
        return this;
    }

    public void setDefenseCharacterId(Integer defenseCharacterId) {
        this.defenseCharacterId = defenseCharacterId;
    }

    public Integer getDefenseTeamId() {
        return defenseTeamId;
    }

    public MRivalEncountCutin defenseTeamId(Integer defenseTeamId) {
        this.defenseTeamId = defenseTeamId;
        return this;
    }

    public void setDefenseTeamId(Integer defenseTeamId) {
        this.defenseTeamId = defenseTeamId;
    }

    public Integer getCutinType() {
        return cutinType;
    }

    public MRivalEncountCutin cutinType(Integer cutinType) {
        this.cutinType = cutinType;
        return this;
    }

    public void setCutinType(Integer cutinType) {
        this.cutinType = cutinType;
    }

    public String getChapter1Text() {
        return chapter1Text;
    }

    public MRivalEncountCutin chapter1Text(String chapter1Text) {
        this.chapter1Text = chapter1Text;
        return this;
    }

    public void setChapter1Text(String chapter1Text) {
        this.chapter1Text = chapter1Text;
    }

    public String getChapter1SoundEvent() {
        return chapter1SoundEvent;
    }

    public MRivalEncountCutin chapter1SoundEvent(String chapter1SoundEvent) {
        this.chapter1SoundEvent = chapter1SoundEvent;
        return this;
    }

    public void setChapter1SoundEvent(String chapter1SoundEvent) {
        this.chapter1SoundEvent = chapter1SoundEvent;
    }

    public String getChapter2Text() {
        return chapter2Text;
    }

    public MRivalEncountCutin chapter2Text(String chapter2Text) {
        this.chapter2Text = chapter2Text;
        return this;
    }

    public void setChapter2Text(String chapter2Text) {
        this.chapter2Text = chapter2Text;
    }

    public String getChapter2SoundEvent() {
        return chapter2SoundEvent;
    }

    public MRivalEncountCutin chapter2SoundEvent(String chapter2SoundEvent) {
        this.chapter2SoundEvent = chapter2SoundEvent;
        return this;
    }

    public void setChapter2SoundEvent(String chapter2SoundEvent) {
        this.chapter2SoundEvent = chapter2SoundEvent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MRivalEncountCutin)) {
            return false;
        }
        return id != null && id.equals(((MRivalEncountCutin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRivalEncountCutin{" +
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
